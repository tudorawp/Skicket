package com.example.skicket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BuySkipassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_skipass)

        // Navigare butoane meniu sus
        findViewById<Button>(R.id.homeButton).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<Button>(R.id.ticketsButton).setOnClickListener {
            startActivity(Intent(this, TicketsActivity::class.java))
        }

        findViewById<Button>(R.id.infoButton).setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

        // Domenii schiabile - fiecare redirecționează către activity_skidomain.xml
        findViewById<Button>(R.id.domeniuSchiabil1Button).setOnClickListener {
            val intent = Intent(this, SkidomainActivity::class.java)
            intent.putExtra("domain_name", "Poiana Brasov")
            intent.putExtra("pricing", "Skipass zilnic - 120 RON")
            startActivity(intent)
        }

        findViewById<Button>(R.id.domeniuSchiabil2Button).setOnClickListener {
            val intent = Intent(this, SkidomainActivity::class.java)
            intent.putExtra("domain_name", "Transalpina")
            intent.putExtra("pricing", "Skipass pe puncte - 100 puncte = 80 RON")
            startActivity(intent)
        }

        findViewById<Button>(R.id.domeniuSchiabil3Button).setOnClickListener {
            val intent = Intent(this, SkidomainActivity::class.java)
            intent.putExtra("domain_name", "Ranca")
            intent.putExtra("pricing", "Skipass zilnic - 90 RON")
            startActivity(intent)
        }

        findViewById<Button>(R.id.domeniuSchiabil4Button).setOnClickListener {
            val intent = Intent(this, SkidomainActivity::class.java)
            intent.putExtra("domain_name", "Predeal")
            intent.putExtra("pricing", "Skipass zilnic - 110 RON")
            startActivity(intent)
        }
    }
}
