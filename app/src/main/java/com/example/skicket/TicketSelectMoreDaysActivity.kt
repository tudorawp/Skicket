package com.example.skicket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONObject
class TicketSelectMoreDaysActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_select_moredays)

        val domain = intent.getStringExtra("domain_name") ?: "necunoscut"
        val label = intent.getStringExtra("ticket_label") ?: "necunoscut"

        val domainName = intent.getStringExtra("domain_name") ?: "Necunoscut"
        findViewById<TextView>(R.id.skipassDomainTitle).text = domainName

        val jsonString = assets.open("ticket_moredays_data.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)

        val container = findViewById<LinearLayout>(R.id.ticketButtonsContainer)

        if (json.has(domainName)) {
            val multiDays = json.getJSONObject(domainName).optJSONArray("multiDays")
            if (multiDays != null) {
                for (i in 0 until multiDays.length()) {
                    val label = multiDays.getString(i)
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
                        // Redirecționează către TicketSelectPeriodActivity
                        setOnClickListener {
                            val intent = Intent(this@TicketSelectMoreDaysActivity, TicketSelectPeriodActivity::class.java)
                            intent.putExtra("domain_name", domainName)
                            intent.putExtra("ticket_label", label)
                            startActivity(intent)
                        }
                    }
                    container.addView(button)
                }
            }
        }
    }
}