package com.example.skicket

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class CartActivity : AppCompatActivity() {


    private lateinit var layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        layout = findViewById(R.id.cartItemsContainer)
        val clearButton = findViewById<Button>(R.id.clearCartButton)
        val addMoreButton = findViewById<Button>(R.id.addMoreButton)
        val backButton = findViewById<Button>(R.id.backButton)

        displayCartItems()

        clearButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove("cart_items").apply()
            layout.removeAllViews()
            Toast.makeText(this, "Coșul a fost golit.", Toast.LENGTH_SHORT).show()
        }

        addMoreButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }

        val payNowButton = findViewById<Button>(R.id.payNowButton)
        payNowButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
            val cartJson = sharedPreferences.getString("cart_items", null)

            if (cartJson.isNullOrEmpty() || JSONArray(cartJson).length() == 0) {
                Toast.makeText(this, "Coșul este gol. Nu poți efectua plata.", Toast.LENGTH_LONG).show()
            } else {
                // Golim coșul înainte de redirecționare (sau după, la alegere)
                sharedPreferences.edit().remove("cart_items").apply()

                val intent = Intent(this, CheckoutSuccessActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        displayCartItems()
    }

    private fun displayCartItems() {
        layout.removeAllViews()

        val sharedPreferences = getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
        val cartJson = sharedPreferences.getString("cart_items", null)

        if (cartJson.isNullOrEmpty()) {
            val emptyText = TextView(this).apply {
                text = "Coșul este gol."
                setTextColor(Color.WHITE)
                textSize = 18f
                setPadding(16, 16, 16, 16)
            }
            layout.addView(emptyText)
            return
        }

        val jsonArray = JSONArray(cartJson)
        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            val domain = item.optString("domain", "N/A")
            val type = item.optString("type", "N/A")
            val label = item.optString("label", "N/A")
            val date = item.optString("startDate", item.optString("date", "N/A"))

            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24, 24, 24, 24)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 16, 0, 0)
                layoutParams = params
                background = getDrawable(android.R.drawable.dialog_holo_light_frame)
            }

            val ticketText = TextView(this).apply {
                text = "Domeniu: $domain\nTip: $type\nOpțiune: $label\nDată: $date"
                setTextColor(Color.BLACK)
                textSize = 16f
            }

            card.addView(ticketText)
            layout.addView(card)
        }
    }
}