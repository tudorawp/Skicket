package com.example.skicket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class TicketDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_details)
        val ticketsButton = findViewById<Button>(R.id.ticketsButton)
        ticketsButton.setOnClickListener {
            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)
        }
        val infoButton = findViewById<Button>(R.id.infoButton)
        infoButton.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
 val moreButton = findViewById<Button>(R.id.homeButton) // acesta este "Mai Multe"
 moreButton.setOnClickListener {
 val intent = Intent(this, MoreOptionsActivity::class.java)
            startActivity(intent)
        }
    }
}
