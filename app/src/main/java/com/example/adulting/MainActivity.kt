package com.example.adulting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.adulting.jdata.database.CardDatabase
import com.example.adulting.jdata.entity.Card
import com.example.adulting.jdata.entity.CardInfo
import com.example.adulting.jdata.entity.CardType
import com.example.adulting.jdata.entity.Response
import com.example.adulting.jdata.modelview.CardViewModel
import com.example.adulting.jdata.repository.CardRepository

class MainActivity : AppCompatActivity() {
    private val IS_DEAD = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View Model Testing
        val viewModel = CardViewModel(application);
        //Log.println(Log.DEBUG, "CARD: ", "Entry  " + i + ": " + viewModel.cards.value.get(0))

        val cardObserver = Observer<List<Card>> { list ->
            for (i in 0..list.size-1) {
                Log.println(Log.DEBUG, "CARD: ", "Entry  " + i + ": " + list.get(i).toString())
            }
        }

        //viewModel.cards.observe(this, cardObserver)
        //Log.println(Log.DEBUG, "TEST", viewModel.cards.value.toString())
        viewModel.relationshipCards.observeForever( cardObserver)



    }

    fun startGame(view: View) {
        // launch the CardSelection Activity
        val myIntent = Intent(this, CardSelection::class.java)
        startActivityForResult(myIntent, IS_DEAD)
    }
}
