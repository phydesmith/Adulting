package com.example.adulting

import android.annotation.TargetApi
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
    private val cards = IntArray(3) {0}
    private val cardId = IntArray(3) {0}


    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        *----------------------------------------------------------------
        * UI Setup
        *----------------------------------------------------------------
        */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mVisible = true


        /*
        *----------------------------------------------------------------
        * Game Logic
        *----------------------------------------------------------------
        */
        val cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java) // from tutorial

        drawCards(cardViewModel);
        observeScore(cardViewModel);

        //  On click listeners for cards
        backCard.setOnClickListener(View.OnClickListener {
            //  Sends information to response activity
            startResponseSelection(cardViewModel, 0)
            // repopulate cards, needs delay so it does not change while user can see
            Handler().postDelayed({drawCards(cardViewModel);}, 1000)
            // update scores
            observeScore(cardViewModel)
        })

        middleCard.setOnClickListener(View.OnClickListener {
            //  Sends information to response activity
            startResponseSelection(cardViewModel, 1)
            // repopulate cards, needs delay so it does not change while user can see
            Handler().postDelayed({drawCards(cardViewModel);}, 1000)
            // update scores
            observeScore(cardViewModel)
        })

        frontCard.setOnClickListener(View.OnClickListener {
            //  Sends information to response activity
            startResponseSelection(cardViewModel, 2)
            // repopulate cards, needs delay so it does not change while user can see
            Handler().postDelayed({drawCards(cardViewModel);}, 1000)
            // update scores
            observeScore(cardViewModel)
        })


        /*
        *----------------------------------------------------------------
        * Testing
        *----------------------------------------------------------------
        */
        //  Test Buttons for icon
        testAddR.setOnClickListener(View.OnClickListener {
            Log.i("Test Button", "Plus 10")
            updateCatValues(10, 'R')
        })
        testSubtractR.setOnClickListener(View.OnClickListener {
            Log.i("Test Button", "Minus 10")
            updateCatValues(-10, 'R')
        })
    }

    /*
    *----------------------------------------------------------------
    * Game Logic Functions
    *----------------------------------------------------------------
    */
    fun observeScore(cardViewModel: CardViewModel) {
        System.out.println("Observing Score");
        // Observe the Score
        var player = Player(-1, 0, 0, 0, 0)
        val observer = Observer<List<Player>> {
            playerStatus.setText(it.get(it.size-1).toString())
            System.out.println("YEET TEST 2 " + it.get(it.size-1))

            updateCatValues(it.get(0).relationship, 'R');
            updateCatValues(it.get(0).education, 'E');
            updateCatValues(it.get(0).health, 'H');
            updateCatValues(it.get(0).wealth, 'W');

            player = it.get(0);

            // starts win/loss if conditions met
            var gameOver = Intent(this, FinalScreen::class.java)
            if (player.relationship <= 0 ||
                player.education <= 0 ||
                player.health <= 0 ||
                player.wealth <= 0 ) {
                System.out.println("Lose conditions met")
                gameOver.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                gameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(gameOver)
            }
            if (player.relationship > 55 ||
                player.education > 55 ||
                player.health > 55 ||
                player.wealth > 55  ) {
                System.out.println("Win conditions met")
                gameOver.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                gameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(gameOver)
            }

        }
        cardViewModel.players.observe(this, observer)
    }

    fun drawCards(cardViewModel: CardViewModel){
        System.out.println("Drawing cards");
        for ( i in 0 until 3) {
            val observer = Observer<List<Card>> { list ->
                cards[i] = random.nextInt(list.size)
                if (i == 0) {
                    backCardTitle.text = list.get(cards[i]).cardName
                    setIcon(backCardIcon, list.get(cards[i]).cardTypeId)
                } else if (i == 1) {
                    middleCardTitle.text = list.get(cards[i]).cardName
                    setIcon(middleCardIcon, list.get(cards[i]).cardTypeId)
                } else {
                    frontCardTitle.text = list.get(cards[i]).cardName
                    setIcon(frontCardIcon, list.get(cards[i]).cardTypeId)
                }
                cardId[i] = list.get(cards[i]).cardInfoId
            }
            cardViewModel.getCardsByType(random.nextInt(4)+1).observe(this, observer)
        }
    }

    fun setIcon(imageView: ImageView, type : Int){
        if (type == 1) {
            imageView.setImageResource(R.drawable.icon_relationships)
        } else if (type == 2){
            imageView.setImageResource(R.drawable.icon_education)
        } else if (type == 3) {
            imageView.setImageResource(R.drawable.icon_health)
        } else {
            imageView.setImageResource(R.drawable.icon_wealth)
        }
    }

    fun startResponseSelection(cardViewModel: CardViewModel, i : Int){
        val myIntent = Intent(this, ChoiceScreen::class.java)
        myIntent.putExtra("cardId", cardId[i])
        startActivity(myIntent)
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

    private fun pxToDp(px: Int): Int {
        return (px / (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }

    /*
    *----------------------------------------------------------------
    * UI/Full Screen Functions
    * Source:
    *----------------------------------------------------------------
    */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

        delayedHide(0)
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
}
