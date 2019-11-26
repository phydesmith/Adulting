package com.example.adulting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

        val cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java) // from tutorial
        cardViewModel.getPlayer(1).observe(this, Observer {}) // this needs to be here so db populates on a clean start up
    }

    fun startGame(view: View) {
        // launch the CardSelection Activity
        val myIntent = Intent(this, CardSelection::class.java)
        startActivityForResult(myIntent, IS_DEAD)
    }
}
