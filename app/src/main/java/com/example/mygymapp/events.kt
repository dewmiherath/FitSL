package com.example.mygymapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class events : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_events)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.yoga).setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@events, Yoga::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.card).setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@events, cardio::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.cyc).setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@events, Cycling::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.box).setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@events, Boxing::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.zum).setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@events, Zumba::class.java)
            startActivity(intent)
        }
    }
}