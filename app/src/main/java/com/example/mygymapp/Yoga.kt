package com.example.mygymapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Yoga : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_yoga)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.bk).setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@Yoga, events::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.rsv).setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@Yoga, Workout::class.java)
            startActivity(intent)
        }
    }
}