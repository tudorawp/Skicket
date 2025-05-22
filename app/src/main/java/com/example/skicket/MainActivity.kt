// În fișierul MainActivity.kt
package com.example.skicket
// Asigură-te că pachetul este corect

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.util.Log
// Pentru mesaje de log, utile la depanare

class MainActivity : AppCompatActivity() {

    // Declara variabilele pentru view-urile cu care vrei să interacționezi
    // Asigură-te că ID-urile corespund cu cele din activity_main.xml
    private lateinit var homeButton: Button
    private lateinit var ticketsButton: Button
    private lateinit var infoButton: Button
    private lateinit var userNameTextView: TextView
    private lateinit var lastSyncTextView: TextView
    private lateinit var skipassStatusTitle: TextView
    private lateinit var skipassCountTextView: TextView

    // Declara variabila pentru butonul "Cumpără skipass"
    private lateinit var buySkipassButton: Button
    private lateinit var transactionHistoryButton: Button

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setează layout-ul pentru MainActivity
        setContentView(R.layout.activity_main)

        // ********** GĂSIREA VIEW-URILOR (findViewById) **********
        // Acestea trebuie apelate DUPĂ setContentView

        homeButton = findViewById(R.id.homeButton)
        ticketsButton = findViewById(R.id.ticketsButton)
        infoButton = findViewById(R.id.infoButton)
        userNameTextView = findViewById(R.id.userNameTextView)
        lastSyncTextView = findViewById(R.id.lastSyncTextView)
        skipassStatusTitle = findViewById(R.id.skipassStatusTitle)
        skipassCountTextView = findViewById(R.id.skipassCountTextView)

        // Găsește butonul "Cumpără skipass"
        buySkipassButton = findViewById(R.id.buySkipassButton)
        transactionHistoryButton = findViewById(R.id.transactionHistoryButton)
        // *********************************************************

        // ********** SETAREA VALORILOR INIȚIALE (opțional) **********
        userNameTextView.text = getString(R.string.nume_prenume1)
        lastSyncTextView.text = getString(R.string.ultima_sincronizare_zz_ll_aaaa_ora_mm_sss)
        skipassStatusTitle.text = getString(R.string.status_skipass1)
        skipassCountTextView.text = getString(R.string._0_skipass_uri_active)
        // Butoanele Cumpără Skipass și Istoric Tranzacții au textul setat în XML prin @string
        // buySkipassButton.text = getString(R.string.cump_r_skipass) // Nu mai e nevoie dacă e în XML
        // transactionHistoryButton.text = getString(R.string.istoric_tranzac_ii) // Nu mai e nevoie dacă e în XML
        // ************************************************************

        // ********** SETAREA CLICK LISTENERS **********

        // Listener pentru butonul "Cumpără skipass"
        buySkipassButton.setOnClickListener {     startActivity(Intent(this, BuySkipassActivity::class.java))
            // finish()
        }

        transactionHistoryButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        // Exemple de alți listeneri (adaptă-i nevoilor tale)
        skipassStatusTitle.setOnClickListener {
            // Exemplu: dacă vrei ca și titlul Status Skipass să ducă la ecranul de bilete

            startActivity(Intent(this, TicketsActivity::class.java))
            // finish() // Opțional
        }

        val ticketsButton = findViewById<Button>(R.id.ticketsButton)
        ticketsButton.setOnClickListener {
            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener { /* Acțiuni pentru Acasă */
            val infoButton = findViewById<Button>(R.id.infoButton)
            infoButton.setOnClickListener {
                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }
            Log.d("MainActivity", "Butonul Acasă a fost apăsat.")
            // Poate deschizi alt Activity sau faci altceva
        }

        ticketsButton.setOnClickListener { /* Acțiuni pentru Bilete */
            Log.d("MainActivity", "Butonul Bilete a fost apăsat.")
            // Exemplu: pornește TicketsActivity

            val intent = Intent(this, TicketsActivity::class.java)

            startActivity(intent)
            // finish() // Opțional
        }

        infoButton.setOnClickListener { /* Acțiuni pentru Info */
            Log.d("MainActivity", "Butonul Info a fost apăsat.")
            // Exemplu: pornește InfoActivity

            val intent = Intent(this, InfoActivity::class.java)

            startActivity(intent)
            // finish() // Opțional
        }

        val moreButton = findViewById<Button>(R.id.homeButton) // acesta este "Mai Multe"
 moreButton.setOnClickListener {
 val intent = Intent(this, MoreOptionsActivity::class.java)
            startActivity(intent)
        }
        val openCartButton = findViewById<Button>(R.id.openCartButton)
        openCartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
        // *********************************************************
    }
    // Poți adăuga aici și alte metode necesare pentru MainActivity
}