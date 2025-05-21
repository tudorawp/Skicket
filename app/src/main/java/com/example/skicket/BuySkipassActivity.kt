package com.example.skicket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class BuySkipassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_skipass)

        // Navigare meniu sus
        findViewById<Button>(R.id.homeButton).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.ticketsButton).setOnClickListener {
            startActivity(Intent(this, TicketsActivity::class.java))
        }
        findViewById<Button>(R.id.infoButton).setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

        // Containerul în care adăugăm dinamic domeniile
        val container = findViewById<LinearLayout>(R.id.skiDomainsContainer)

        // Citire JSON
        val jsonString = assets.open("ski_domains.json").bufferedReader().use { it.readText() }
        val domainArray = JSONArray(jsonString)

        for (i in 0 until domainArray.length()) {
            val domain = domainArray.getJSONObject(i)

            val name = domain.getString("name")
            val validFrom = domain.getString("validFrom")
            val validTo = domain.getString("validTo")
            val ticketType = domain.getString("ticketType")
            val ticketId = domain.getString("ticketId")
            val liftsArray = domain.getJSONArray("lifts")

            val lifts = mutableListOf<String>()
            for (j in 0 until liftsArray.length()) {
                lifts.add(liftsArray.getString(j))
            }

            // Creează un LinearLayout pentru fiecare domeniu
            val domainLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(16, 16, 16, 16)
                setBackgroundColor(0xFF07549B.toInt())
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 16, 0, 0)
                layoutParams = params
            }

            // Butonul cu numele domeniului
            val button = Button(this).apply {
                text = name
                setBackgroundColor(0xFF07549B.toInt())
                setTextColor(0xFFBBDEFB.toInt())
                textSize = 18f
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                setOnClickListener {
                    val intent = Intent(this@BuySkipassActivity, SkidomainActivity::class.java).apply {
                        putExtra("domainName", name)
                        putExtra("validFrom", validFrom)
                        putExtra("validTo", validTo)
                        putExtra("ticketType", ticketType)
                        putExtra("ticketId", ticketId)
                        putStringArrayListExtra("liftList", ArrayList(lifts))
                    }
                    startActivity(intent)
                }
            }

            // Imaginea (statică) pentru fiecare domeniu
            val image = ImageView(this).apply {
                setImageResource(R.drawable.logoskiket)
                layoutParams = LinearLayout.LayoutParams(150, 150)
            }

            // Adaugă butonul și imaginea în layout
            domainLayout.addView(button)
            domainLayout.addView(image)

            // Adaugă acest layout în container
            container.addView(domainLayout)
        }
    }
}
