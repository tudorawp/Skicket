package com.example.skicket

import android.content.Intent
import android.content.Context
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class TicketSelectPeriodActivity : AppCompatActivity() {


    private var startDate: Calendar? = null
    private var endDate: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_select_period)

        val domainName = intent.getStringExtra("domain_name") ?: "N/A"
        val ticketLabel = intent.getStringExtra("ticket_label") ?: "N/A"
        val ticketType = intent.getStringExtra("ticket_type") ?: "N/A" // Adaugă tipul biletului

        val selectedText = findViewById<TextView>(R.id.selected_option_text)
        selectedText.text = "Domeniu: $domainName\nTip: $ticketLabel"

        val setStartButton = findViewById<Button>(R.id.setStartDateButton)
        val setEndButton = findViewById<Button>(R.id.setEndDateButton)
        val confirmButton = findViewById<Button>(R.id.confirmButton)
        val backButton = findViewById<Button>(R.id.backButton)
        val goToMainButton = findViewById<Button>(R.id.goToMainButton)

        goToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Închide activitatea curentă
        }


        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        setStartButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                startDate = Calendar.getInstance().apply { set(year, month, day) }
                setStartButton.text = "Început: ${formatter.format(startDate!!.time)}"
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        setEndButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                endDate = Calendar.getInstance().apply { set(year, month, day) }
                setEndButton.text = "Sfârșit: ${formatter.format(endDate!!.time)}"
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        confirmButton.setOnClickListener {
            if (startDate == null || endDate == null) {
                Toast.makeText(this, "Selectează ambele date!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val diffMillis = endDate!!.timeInMillis - startDate!!.timeInMillis
            val diffDays = diffMillis / (1000 * 60 * 60 * 24)

            if (diffDays < 0) {
                Toast.makeText(this, "Data de sfârșit nu poate fi înaintea celei de început!", Toast.LENGTH_LONG).show()
            } else if (diffDays > 5) {
                Toast.makeText(this, "Perioada nu poate depăși 5 zile. Reîncearcă!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Perioadă validă: $diffDays zile", Toast.LENGTH_SHORT).show()
                // Adaugă biletul în coș
                val sharedPrefs = getSharedPreferences("cart", MODE_PRIVATE)
                val editor = sharedPrefs.edit()
                val currentCart = sharedPrefs.getString("cartItems", "[]")
                val jsonArray = JSONArray(currentCart)

                // creezi un obiect JSON cu datele biletului
                val ticketObj = JSONObject().apply {
                    put("domain", domainName)
                    put("type", ticketType)
                    put("label", ticketLabel) // sau opțiunea aleasă
                    put("date", "${formatter.format(startDate!!.time)} - ${formatter.format(endDate!!.time)}") // adaugă perioada selectată
                }

                // adaugi biletul în array și salvezi
                jsonArray.put(ticketObj)
                editor.putString("cartItems", jsonArray.toString())
                editor.apply()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    fun saveTicketToCart(context: Context, ticketData: JSONObject) {
        val sharedPreferences = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val existingData = sharedPreferences.getString("cart_items", "[]")
        val cartArray = JSONArray(existingData)

        cartArray.put(ticketData)
        editor.putString("cart_items", cartArray.toString())
        editor.apply()
    }
}