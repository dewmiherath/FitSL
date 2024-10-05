package com.example.mygymapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Delay for 2 seconds (2000 milliseconds)
        Handler(Looper.getMainLooper()).postDelayed({
            // Intent to start Role activity
            val intent = Intent(this@MainActivity, Role::class.java)
            startActivity(intent)
            finish()  // Call finish() to close the current activity
        }, 2000)  // 2000 milliseconds = 2 seconds
    }
}
