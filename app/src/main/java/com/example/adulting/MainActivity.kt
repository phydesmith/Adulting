package com.example.adulting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.adulting.data.appdatabase.AppDatabase
import com.example.adulting.data.entities.CardType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val IS_DEAD = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DATABASE TESTING
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "cards"
        ).allowMainThreadQueries().build() // allowMainThreadQueries() needs to be removed eventually - db needs to run on separate thread
        var relCard_Type = CardType(1, "Relationships")
        var eduCard_Type = CardType(2, "Education")
        var helCard_Type = CardType(3, "Health")
        var welCard_Type = CardType( 4, "Wealth")
        /*
        db.cardTypeDao().insertAll(
            relCard_Type,
            eduCard_Type,
            helCard_Type,
            welCard_Type
        )
        */
        var cardTypes = db.cardTypeDao().getAll()
        main_db_test_Text.setText(cardTypes.get(0).typeDescription.toString());


    }

    fun startGame(view: View) {
        // launch the CardSelection Activity
        val myIntent = Intent(this, CardSelection::class.java)
        startActivityForResult(myIntent, IS_DEAD)
    }
}
