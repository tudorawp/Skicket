package com.example.skicket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class TicketSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_selection)


        // Preluare informații din intent
        val domainName = intent.getStringExtra("domain_name") ?: "Domeniu Necunoscut"
        val ticketType = intent.getStringExtra("ticket_type") ?: "Tip Nespecificat"

        // Setare titluri în layout
        findViewById<TextView>(R.id.skipassDomainTitle).text = domainName
        findViewById<TextView>(R.id.skipassTypeTitle).text = ticketType

        val buttonsContainer = findViewById<LinearLayout>(R.id.ticketButtonsContainer)

        // Citire JSON din assets
        val jsonString = assets.open("ticket_selection_data.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)

        if (jsonObject.has(domainName)) {
            val ticketTypes = jsonObject.getJSONObject(domainName)
            if (ticketTypes.has(ticketType)) {
                val labelsArray = ticketTypes.getJSONArray(ticketType)

                for (i in 0 until labelsArray.length()) {
                    val label = labelsArray.getString(i)

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

                        // La apăsare, deschidem activity_ticket_select_period1.xml și trimitem detaliile
                        setOnClickListener {
                            val intent = Intent(this@TicketSelectionActivity, TicketSelectPeriod1Activity::class.java)
                            intent.putExtra("domain_name", domainName)
                            intent.putExtra("ticket_type", ticketType)
                            intent.putExtra("ticket_option", label)
                            startActivity(intent)
                        }
                    }

                    buttonsContainer.addView(button)
                }
            }
        }

        // Butonul Înapoi (dacă există în layout)
        findViewById<Button?>(R.id.backButton)?.setOnClickListener {
            finish()
        }

        // Butonul "Mai multe zile"
        findViewById<Button?>(R.id.moreDaysButton)?.setOnClickListener {
            val intent = Intent(this, TicketSelectMoreDaysActivity::class.java)
            intent.putExtra("domain_name", domainName)
            intent.putExtra("ticket_type", ticketType)
            startActivity(intent)
        }
    }
}