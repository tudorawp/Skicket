package com.example.skicket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class SkidomainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skidomain)

        // Preluare date din intent
        val domainName = intent.getStringExtra("domainName")
        val validFrom = intent.getStringExtra("validFrom")
        val validTo = intent.getStringExtra("validTo")
        val ticketType = intent.getStringExtra("ticketType")
        val ticketId = intent.getStringExtra("ticketId")

        // Setare text în TextView-uri
        findViewById<TextView>(R.id.domainNameText).text = domainName
        findViewById<TextView>(R.id.dateStartText).text = "Valabil de la: $validFrom"
        findViewById<TextView>(R.id.dateEndText).text = "Valabil pana la: $validTo"
        findViewById<TextView>(R.id.ticketTypeText).text = "Tip bilet: $ticketType"
        findViewById<TextView>(R.id.ticketIdText).text = "ID: $ticketId"

        val backToBuyButton = findViewById<Button>(R.id.backToBuyButton)
        backToBuyButton.setOnClickListener {
            startActivity(Intent(this, BuySkipassActivity::class.java))
            finish() // optional, dacă vrei să închizi activitatea curentă
        }
    }
}
