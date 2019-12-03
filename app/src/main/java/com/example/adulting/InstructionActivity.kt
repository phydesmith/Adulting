package com.example.adulting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class InstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)
    }
    // instructions
    fun GoHome (view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

