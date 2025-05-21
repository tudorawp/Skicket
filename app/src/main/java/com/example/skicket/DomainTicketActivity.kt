package com.example.skicket

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DomainTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domain_ticket)
        val domainName = intent.getStringExtra("domain_name") ?: "Domeniu Schiabil"
        findViewById<TextView>(R.id.domainNameTitle).text = domainName
    }
}

