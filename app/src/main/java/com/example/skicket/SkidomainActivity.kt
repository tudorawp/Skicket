package com.example.skicket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class SkidomainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skidomain)


        val domainName = intent.getStringExtra("domainName")

        val jsonString = loadJSONFromAssets("ski_domains.json")
        val domainArray = JSONArray(jsonString)
        val domain = (0 until domainArray.length())
            .map { domainArray.getJSONObject(it) }
            .firstOrNull { it.getString("name") == domainName }

        if (domainName != null && domain != null) {
            val name = domain.getString("name")
            val validFrom = domain.optString("validFrom", "-")
            val validTo = domain.optString("validTo", "-")
            val ticketType = domain.getString("ticketType")
            val ticketId = generateTicketId()
            val lifts = domain.getJSONArray("lifts")

            findViewById<TextView>(R.id.domainNameText).text = name
            findViewById<TextView>(R.id.operatingPeriodText).text = "Valabil: $validFrom - $validTo"

            val liftListContainer = findViewById<LinearLayout>(R.id.liftListContainer)
            for (i in 0 until lifts.length()) {
                val liftName = lifts.getString(i)
                val textView = TextView(this).apply {
                    text = liftName
                    setTextColor(resources.getColor(android.R.color.holo_blue_bright))
                    textSize = 18f
                    setPadding(10, 10, 10, 10)
                    setBackgroundColor(resources.getColor(android.R.color.transparent))
                }
                liftListContainer.addView(textView)
            }
        }

        // Butonul "BILETE" te duce înapoi în BuySkipassActivity
        findViewById<Button>(R.id.backToBuyButton).setOnClickListener {
            val domainName = intent.getStringExtra("domainName")
            val intent = Intent(this, DomainTicketActivity::class.java)
            intent.putExtra("domain_name", domainName)
            startActivity(intent)
            finish()
        }

        // Meniu (hamburger) te duce înapoi în BuySkipassActivity
        findViewById<Button>(R.id.menuButton).setOnClickListener {
            startActivity(Intent(this, BuySkipassActivity::class.java))
            finish()
        }

        val locationButton = findViewById<Button>(R.id.locationButton)
        locationButton.setOnClickListener {
            Toast.makeText(this, "🙊COMING SOON🙊", Toast.LENGTH_SHORT).show()
        }

    }

    private fun loadJSONFromAssets(filename: String): String {
        val inputStream: InputStream = assets.open(filename)
        return inputStream.bufferedReader().use { it.readText() }
    }

    private fun generateTicketId(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..12).map { chars.random() }.joinToString("")
    }
}