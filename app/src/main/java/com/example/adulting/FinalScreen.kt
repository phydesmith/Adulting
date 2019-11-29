package com.example.adulting

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

        val win = getIntent().getBooleanExtra("win", false)

        if (win){
            finalResults.text = "You Won!"
        } else {
            finalResults.text = "You Lost!"
        }

        finalButton.setOnClickListener(View.OnClickListener {
            val cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java) // from tutorial
            cardViewModel.updatePlayer(Player(1, 25, 25, 25, 25))
            finish()
        })
    }
}
