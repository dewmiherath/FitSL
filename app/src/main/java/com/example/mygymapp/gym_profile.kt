package com.example.mygymapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class gym_profile : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var gymNameTextView: TextView
    private lateinit var gymAddressTextView: TextView
    private lateinit var gymEmailTextView: TextView
    private lateinit var gymPhoneTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gym_profile)

        // Initialize views
        gymNameTextView = findViewById(R.id.gymName)
        gymAddressTextView = findViewById(R.id.gymAddress)
        gymEmailTextView = findViewById(R.id.gymEmail)
        gymPhoneTextView = findViewById(R.id.gymPhone)

        // Get gym ID from intent
        val gymId = intent.getStringExtra("gymId")

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().getReference("Gym")

        // Fetch gym details from Firebase
        if (gymId != null) {
            database.child(gymId).get().addOnSuccessListener { snapshot ->
                val gym = snapshot.getValue(Gym::class.java)
                gym?.let {
                    gymNameTextView.text = it.name
                    gymAddressTextView.text = it.address
                    gymEmailTextView.text = it.email
                    gymPhoneTextView.text = it.phone
                }
            }.addOnFailureListener {
                // Handle failure
            }
        }
    }
}
