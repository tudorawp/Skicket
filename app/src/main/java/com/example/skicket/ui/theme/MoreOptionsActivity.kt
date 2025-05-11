package com.example.skicket

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MoreOptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_options)

        val listView = findViewById<ListView>(R.id.optionsList)

        // Opțiuni extrase din drawer_menu.xml (manual)
        val options = listOf("Profil", "Setări", "Despre aplicație")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> Toast.makeText(this, "Ai apăsat pe Profil", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this, "Ai apăsat pe Setări", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this, "Ai apăsat pe Despre aplicație", Toast.LENGTH_SHORT).show()
            }
        }

        val backButton = findViewById<Button>(R.id.backToMainButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // opțional: închide activitatea curentă
        }
    }
}
