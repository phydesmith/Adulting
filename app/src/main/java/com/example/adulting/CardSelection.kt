package com.example.adulting

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.adulting.jdata.entity.Card
import com.example.adulting.jdata.entity.Player
import com.example.adulting.jdata.modelview.CardViewModel
import kotlinx.android.synthetic.main.activity_card_selection.*
import kotlin.math.roundToInt


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class CardSelection : AppCompatActivity() {
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

    //  Game Vars
    private val random = java.util.Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_card_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mVisible = true

        // Game Logic
        val cards = IntArray(3) {0}
        val cardId = IntArray(3) {0}

        val cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java) // from tutorial

        //  Generate Card Titles
        for ( i in 0 until 3) {
            val observer = Observer<List<Card>> { list ->
                cards[i] = random.nextInt(list.size)
                if (i == 0) {
                    backCardTitle.text = list.get(cards[i]).cardName
                } else if (i == 1) {
                    middleCardTitle.text = list.get(cards[i]).cardName
                } else {
                    frontCardTitle.text = list.get(cards[i]).cardName
                }
                cardId[i] = list.get(cards[i]).cardInfoId
            }
            cardViewModel.getCardsByType(random.nextInt(4)+1).observe(this, observer)
        }


        // Observe the Score
        val observer = Observer<List<Player>> {
            playerStatus.setText(it.get(it.size-1).toString())
            System.out.println("YEET TEST 2 " + it.get(it.size-1))

            updateCatValues(it.get(0).relationship, 'R');
            updateCatValues(it.get(0).education, 'E');
            updateCatValues(it.get(0).health, 'H');
            updateCatValues(it.get(0).wealth, 'W');
            
        }
        cardViewModel.players.observe(this, observer)

        //  Test Buttons for icon
        testAddR.setOnClickListener(View.OnClickListener {
            Log.i("Test Button", "Plus 10")
            updateCatValues(10, 'R')
        })
        testSubtractR.setOnClickListener(View.OnClickListener {
            Log.i("Test Button", "Minus 10")
            updateCatValues(-10, 'R')
        })

        //  On click listeners for cards
        backCard.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this, ChoiceScreen::class.java)
            myIntent.putExtra("cardId", cardId[0])
            myIntent.putExtra("card", cards[0])
            startActivityForResult(myIntent, 1234)
            delayedHide(0)
            recreate()
        })

        /*
        middleCard.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this, ChoiceScreen::class.java)
            myIntent.putExtra("cardId", cardId[1])
            myIntent.putExtra("card", cards[1])
            startActivityForResult(myIntent, 1234)
            delayedHide(0)
        })
        frontCard.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this, ChoiceScreen::class.java)
            myIntent.putExtra("cardId", cardId[2])
            myIntent.putExtra("card", cards[2])
            startActivityForResult(myIntent, 1234)
            delayedHide(0)
        })
        */

    }

    // game functions
    private fun getTypes() : IntArray{
        val types = IntArray(3){0}
        for ( i in 0 until 3 ){
            types[i] = (random.nextInt(4)+1)
        }
        return types
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

        var oldHeight = updateValue //pxToDp(valueToUpdate.layoutParams.height) + updateValue

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


    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        fullscreen_content_controls.visibility = View.GONE
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
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
