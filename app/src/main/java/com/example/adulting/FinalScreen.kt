package com.example.adulting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.adulting.jdata.entity.Player
import com.example.adulting.jdata.modelview.CardViewModel
import kotlinx.android.synthetic.main.activity_final_screen.*

class FinalScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)

        observeFinalScore( ViewModelProviders.of(this).get(CardViewModel::class.java) )

        finalButton.setOnClickListener(View.OnClickListener {
            val cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java) // from tutorial
            cardViewModel.updatePlayer(Player(1, 25, 25, 25, 25))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }

    /*
    *----------------------------------------------------------------
    * Game Logic Functions
    *----------------------------------------------------------------
    */

    fun observeFinalScore(cardViewModel: CardViewModel) {
        System.out.println("Observing Score");
        // Observe the Score
        var player = Player(-1, 0, 0, 0, 0)
        val observer = Observer<List<Player>> {
            System.out.println("YEET TEST 2 " + it.get(it.size-1))

            player = it.get(0);

            // updates tru/false textview
            if (player.relationship <= 0 ||
                player.education <= 0 ||
                player.health <= 0 ||
                player.wealth <= 0 ) {
                System.out.println("Lose conditions met")
                finalResults.setText("You lose.")

            }
            if (player.relationship > 55 ||
                player.education > 55 ||
                player.health > 55 ||
                player.wealth > 55  ) {
                System.out.println("Win conditions met")
                finalResults.setText("You win!")
            }

        }
        cardViewModel.players.observe(this, observer)
    }
}
