package com.example.skicket

import android.app.DatePickerDialog
import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

private fun saveTicketToCart(context: Context, ticketData: JSONObject) {
    val prefs = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
    val existing = prefs.getString("cart_items", "[]")
    val array = JSONArray(existing)
    array.put(ticketData)
    prefs.edit().putString("cart_items", array.toString()).apply()
}
class TicketSelectPeriod1Activity : AppCompatActivity() {


    private lateinit var setDateButton: Button
    private lateinit var confirmButton: Button
    private lateinit var selectedDateText: TextView
    private lateinit var optionTextView: TextView

    private var selectedDate: Calendar? = null
    private lateinit var ticketOption: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_select_period1)

        setDateButton = findViewById(R.id.setStartDateButton)
        confirmButton = findViewById(R.id.confirmPeriodButton)

        // Afișăm biletul selectat într-un TextView dinamic
        optionTextView = TextView(this).apply {
            textSize = 20f
            setTextColor(0xFFCCFF33.toInt())
            setPadding(0, 32, 0, 16)
        }

        // Afișăm data selectată
        selectedDateText = TextView(this).apply {
            textSize = 18f
            setTextColor(0xFFFFFFFF.toInt())
            setPadding(0, 16, 0, 0)
        }

        // Adăugăm textview-urile în layout dinamic
        val layout = findViewById<LinearLayout>(R.id.periodSelectionLayout)
        layout.addView(optionTextView, 0)
        layout.addView(selectedDateText, 1)

        // Preluăm datele din intent
        val domainName = intent.getStringExtra("domain_name") ?: "Necunoscut"
        val ticketType = intent.getStringExtra("ticket_type") ?: "Necunoscut"
        ticketOption = intent.getStringExtra("ticket_option") ?: "Neselectat"

        optionTextView.text = "Bilet selectat: $ticketOption"

        setDateButton.setOnClickListener {
            showDatePickerDialog()
        }

        findViewById<Button>(R.id.confirmPeriodButton).setOnClickListener {
            val selectedDate = this.selectedDate?.let { formatDate(it) } // obține data selectată de utilizator
            if (selectedDate != null) {
                val ticket = JSONObject().apply {
                    put("domain", domainName)
                    put("type", ticketType)
                    put("label", ticketOption)
                    put("startDate", selectedDate)
                    put("quantity", 1)
                }

                saveTicketToCart(this, ticket)

                Toast.makeText(this, "Bilet adăugat în coș!", Toast.LENGTH_SHORT).show()

                // Navighează înapoi sau către CartActivity dacă vrei:
                // startActivity(Intent(this, CartActivity::class.java))
                // finish()
            } else {
                Toast.makeText(this, "Te rog selectează o dată!", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }

        val goToMainButton = findViewById<Button>(R.id.goToMainButton)
        goToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }


    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, day ->
                selectedDate = Calendar.getInstance().apply {
                    set(year, month, day)
                }
                selectedDateText.text = "Data selectată: ${formatDate(selectedDate!!)}"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun formatDate(calendar: Calendar): String {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)
        return "$day/$month/$year"
    }
}