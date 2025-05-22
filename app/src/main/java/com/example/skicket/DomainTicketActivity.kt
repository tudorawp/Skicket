package com.example.skicket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class DomainTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domain_ticket)


        val domainName = intent.getStringExtra("domain_name")
        Log.d("DomainDebug", "Primit din intent: $domainName")

        if (domainName.isNullOrEmpty()) {
            Toast.makeText(this, "Nume domeniu invalid", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        findViewById<TextView>(R.id.domainNameTitle).text = domainName

        val container = findViewById<LinearLayout>(R.id.ticketTypeContainer)

        val jsonString = assets.open("tickets_by_domain.json").bufferedReader().use { it.readText() }
        Log.d("DomainDebug", "Loaded JSON: $jsonString")
        val domainsArray = JSONArray(jsonString)

        var matched = false

        for (i in 0 until domainsArray.length()) {
            val domain = domainsArray.getJSONObject(i)
            val jsonDomainName = domain.getString("domainName")
            Log.d("DomainDebug", "Compar cu: $jsonDomainName")

            if (jsonDomainName.equals(domainName, ignoreCase = true)) {
                matched = true
                val tickets = domain.getJSONArray("tickets")

                for (j in 0 until tickets.length()) {
                    val ticket = tickets.getJSONObject(j)
                    val title = ticket.getString("title")
                    val descriptionArray = ticket.getJSONArray("description")
                    val type = ticket.getString("type")

                    val layout = LinearLayout(this).apply {
                        orientation = LinearLayout.VERTICAL
                        setBackgroundColor(0xFF111111.toInt())
                        setPadding(24, 24, 24, 24)
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 0, 0, 32)
                        layoutParams = params
                    }

                    val titleView = TextView(this).apply {
                        text = title
                        textSize = 20f
                        setTextColor(0xFFFFFFFF.toInt())
                        setPadding(0, 0, 0, 16)
                        setTypeface(null, android.graphics.Typeface.BOLD)
                        setBackgroundColor(0xFF07549B.toInt())
                        setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER)
                        isClickable = true
                        isFocusable = true
                        setOnClickListener {
                            val intent = if (type.equals("interval_multe_zile", ignoreCase = true)) {
                                Intent(this@DomainTicketActivity, TicketSelectMoreDaysActivity::class.java)
                            } else {
                                // Pentru toate celelalte tipuri, inclusiv "o_singura_zi"
                                // și orice alte tipuri care ar putea fi adăugate
                                Intent(this@DomainTicketActivity, TicketSelectionActivity::class.java) // Modificat pentru a include și "o_singura_zi"
                            }
                            intent.putExtra("ticket_type", type)
                            intent.putExtra("domain_name", domainName)
                            startActivity(intent)
                        }
                    }

                    layout.addView(titleView)

                    for (k in 0 until descriptionArray.length()) {
                        val bullet = descriptionArray.getString(k)
                        val descView = TextView(this).apply {
                            text = bullet
                            setTextColor(0xFFCCFF33.toInt())
                            textSize = 16f
                            setPadding(0, 4, 0, 4)
                        }
                        layout.addView(descView)
                    }

                    container.addView(layout)
                }

                break
            }
        }

        if (!matched) {
            Log.e("DomainDebug", "Niciun domeniu potrivit cu $domainName")
            Toast.makeText(this, "Nu s-au găsit bilete pentru acest domeniu", Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}