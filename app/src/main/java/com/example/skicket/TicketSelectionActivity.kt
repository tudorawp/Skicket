package com.example.skicket

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class TicketSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_selection)

        val domainName = intent.getStringExtra("domain_name") ?: ""
        val ticketType = intent.getStringExtra("ticket_type") ?: ""

        findViewById<TextView>(R.id.skipassDomainTitle).text = domainName
        findViewById<TextView>(R.id.skipassTypeTitle).text = ticketType

        val buttonsContainer = findViewById<LinearLayout>(R.id.ticketButtonsContainer)

        val jsonString = assets.open("ticket_selection_data.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)

        if (jsonObject.has(domainName)) {
            val domainObject = jsonObject.getJSONObject(domainName)
            if (domainObject.has(ticketType)) {
                val buttonsArray = domainObject.getJSONArray(ticketType)

                for (i in 0 until buttonsArray.length()) {
                    val label = buttonsArray.getString(i)
                    val button = Button(this).apply {
                        text = label
                        setBackgroundColor(0xFF07549B.toInt())
                        setTextColor(0xFFFFFFFF.toInt())
                        textSize = 16f
                        setPadding(24, 24, 24, 24)
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            setMargins(0, 16, 0, 0)
                        }
                    }
                    buttonsContainer.addView(button)
                }
            } else {
                Toast.makeText(this, "Tipul de bilet nu a fost găsit în JSON.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Domeniul nu a fost găsit în JSON.", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}