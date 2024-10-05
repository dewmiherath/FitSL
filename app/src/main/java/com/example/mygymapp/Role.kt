package com.example.mygymapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Role : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_role)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@Role, Sign_in::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            // Intent to redirect to gym_list interface
            val intent = Intent(this@Role, Sign_in::class.java)
            startActivity(intent)
        }
    }
}