package com.example.skicket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TicketsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        // Buton de back
        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish() // Închide activitatea și revii la MainActivity
        }

        // Afișare simplă (temporară)
        findViewById<TextView>(R.id.temporaryText).text = "Aici vor apărea biletele mai târziu"

        val ticketCard = findViewById<LinearLayout>(R.id.ticketCard)
        ticketCard.setOnClickListener {
            val intent = Intent(this, TicketDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}