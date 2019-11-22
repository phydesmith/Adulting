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
/*
        var cardType  = CardType("Relationship");
        var cardInfo  = CardInfo(1,"name1", "relationship problem ");
        var response  = Response(1,"Relationship response",
            false,
            0, 0, 0, 0,
            10, 0, 0, 0);




        viewModel.insertCardType(cardType);
        viewModel.insertCardInfo(cardInfo);
        viewModel.insertResponses(response)

        val cardObserver = Observer<List<Card>> { list ->
            Log.println(Log.DEBUG, "CARD: ", list.get(0).toString() )
        }


*/
        val viewModel = CardViewModel(application);

        val cardTypeObserver = Observer<List<CardType>> { list ->
            for (i in 0..list.size-1){
               Log.println(Log.DEBUG, "get " + i + " : ", list.get(i).toString() )
            }
        }
        val infoObserver = Observer<List<CardInfo>> { list ->
            for (i in 0..list.size-1){
                Log.println(Log.DEBUG, "get " + i + " : ", list.get(i).toString() )
            }
        }
        val responseObserver = Observer<List<Response>> { list ->
            for (i in 0..list.size-1){
                Log.println(Log.DEBUG, "get " + i + " : ", list.get(i).toString() )
            }
        }
/*
        val cardObserver = Observer<List<Card>> { list ->
            //for (i in 0..list.size-1) {
                Log.println(Log.DEBUG, "CARD: ", list.get(0).toString())
            //}
        }

 */
        viewModel.allTypes.observe(this, cardTypeObserver)
        viewModel.allInfo.observe(this, infoObserver)
        viewModel.allResponses.observe(this, responseObserver)

        //viewModel.cards.observe(this, cardObserver)




    }

    fun startGame(view: View) {
        // launch the CardSelection Activity
        val myIntent = Intent(this, CardSelection::class.java)
        startActivityForResult(myIntent, IS_DEAD)
    }
}
