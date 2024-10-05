package com.example.mygymapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add_gym : AppCompatActivity() {

    private lateinit var gymNameEditText: EditText
    private lateinit var gymAddressEditText: EditText
    private lateinit var gymEmailEditText: EditText
    private lateinit var gymPhoneEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var backButton: Button

    private lateinit var database: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_gym)

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().getReference("Gym")

        // Initialize views
        gymNameEditText = findViewById(R.id.name)
        gymAddressEditText = findViewById(R.id.addr)
        gymEmailEditText = findViewById(R.id.mail)
        gymPhoneEditText = findViewById(R.id.phone)
        submitButton = findViewById(R.id.submit)
        backButton = findViewById(R.id.bck)

        // Set up window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backButton.setOnClickListener {
            // Intent to redirect to gym_user interface
            val intent = Intent(this@add_gym, Role::class.java)
            startActivity(intent)
        }
        //test

        submitButton.setOnClickListener {
            // Get input values
            val gymName = gymNameEditText.text.toString().trim()
            val gymAddress = gymAddressEditText.text.toString().trim()
            val gymEmail = gymEmailEditText.text.toString().trim()
            val gymPhone = gymPhoneEditText.text.toString().trim()

            // Validate input
            if (gymName.isEmpty() || gymAddress.isEmpty() || gymEmail.isEmpty() || gymPhone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a Gym object
            val gymId = database.push().key // Generate a unique ID for the gym
            val gym = Gym(gymId, gymName, gymAddress, gymEmail, gymPhone)

            // Push data to Firebase
            gymId?.let {
                database.child(it).setValue(gym).addOnSuccessListener {
                    Toast.makeText(this, "Gym added successfully", Toast.LENGTH_SHORT).show()
                    // Redirect to gym_profile with the gym ID
                    val intent = Intent(this@add_gym, Gym_pro::class.java)
                    intent.putExtra("GYM_ID", gymId) // Pass the gym ID
                    startActivity(intent)
                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to add gym: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
