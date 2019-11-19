package com.example.adulting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.adulting.jdata.database.CardDatabase
import com.example.adulting.jdata.entity.CardInfo
import com.example.adulting.jdata.entity.CardType
import com.example.adulting.jdata.repository.CardRepository

class MainActivity : AppCompatActivity() {
    private val IS_DEAD = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        // DATABASE TESTING
        // KOTLIN
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "cards"
        ).allowMainThreadQueries().build() // allowMainThreadQueries() needs to be removed eventually - db needs to run on separate thread
        var relCard_Type = CardType(1, "Relationships")
        var eduCard_Type = CardType(2, "Education")
        var helCard_Type = CardType(3, "Health")
        var welCard_Type = CardType( 4, "Wealth")

        db.cardTypeDao().insertAll(
            relCard_Type,
            eduCard_Type,
            helCard_Type,
            welCard_Type
        )
        var cardTypes = db.cardTypeDao().getAll()
        main_db_test_Text.setText(cardTypes.get(0).typeDescription.toString());
         */

        var cardType  = CardType("Relationship");
        val repository = CardRepository(application)
        repository.insert(cardType)

        val cardTypeObserver = Observer<List<CardType>> { list ->
            Log.println(Log.DEBUG, "get: ", list.get(0).toString() )
        }

        repository.allCardTypes.observe(this, cardTypeObserver);


    }

    fun startGame(view: View) {
        // launch the CardSelection Activity
        val myIntent = Intent(this, CardSelection::class.java)
        startActivityForResult(myIntent, IS_DEAD)
    }
}
