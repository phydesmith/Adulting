package com.example.adulting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.adulting.jdata.entity.Card
import com.example.adulting.jdata.modelview.CardViewModel
import kotlinx.android.synthetic.main.activity_choice_screen.*
import java.security.AccessController.getContext
import kotlin.math.roundToInt


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ChoiceScreen : AppCompatActivity() {
    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        fullscreen_content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
        fullscreen_content_controls.visibility = View.VISIBLE
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val mDelayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    // Game vars
    private lateinit var cardViewModel : CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_choice_screen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mVisible = true

        val type = getIntent().getIntExtra("type", 0)
        val card = getIntent().getIntExtra("card", 0)

        cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java) // from tutorial
        val observer = Observer<List<Card>> { list ->
            cardTitle.setText(list.get(0).cardName)
            for(i in 0 until list.size ) {
                if (i == 0) {
                    choice1.setText(list.get(i).response)
                } else if (i == 1){
                    choice2.setText(list.get(i).response)
                } else {
                    choice3.setText(list.get(i).response)
                }
            }
        }
        cardViewModel.getCardByTypeAndId(type, card).observe(this, observer)


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(0)
    }

    private fun updateCatValues(updateValue: Int, catToUpdate: Char) {
        lateinit var valueToUpdate : ImageView
        when (catToUpdate) {
            'R' -> valueToUpdate = valueRelationships
            'E' -> valueToUpdate = valueEducation
            'H' -> valueToUpdate = valueHealth
            'W' -> valueToUpdate = valueWealth
        }

        var oldHeight = pxToDp(valueToUpdate.layoutParams.height) + updateValue

        Log.i("Preston", "Math Starts Here: $oldHeight")
        when {
            oldHeight in 0..55 -> valueToUpdate.layoutParams.height = dpToPx(oldHeight)
            oldHeight > 55 -> valueToUpdate.layoutParams.height = dpToPx(55)
            else -> valueToUpdate.layoutParams.height = 0
        }
        if (oldHeight > 15) {
            valueToUpdate.setBackgroundColor(ContextCompat.getColor(this, R.color.darkBlue))
        } else {
            valueToUpdate.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
        }

        valueToUpdate.requestLayout()
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        fullscreen_content_controls.visibility = View.GONE
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
        fullscreen_content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        mVisible = true

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }

    private fun pxToDp(px: Int): Int {
        return (px / (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}
