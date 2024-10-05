package com.example.mygymapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Gym_pro : AppCompatActivity() {
        private lateinit var database: DatabaseReference

    private lateinit var gymNameTextView: TextView
    private lateinit var gymAddressTextView: TextView
    private lateinit var gymPhoneTextView: TextView
    private lateinit var gymEmailTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gym_pro)

        // Initialize views
        gymNameTextView = findViewById(R.id.gymName)
        gymAddressTextView = findViewById(R.id.gymAddress)
        gymPhoneTextView = findViewById(R.id.gymPhone)
        gymEmailTextView = findViewById(R.id.gymEmail)

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().getReference("Gym")

        // Retrieve the latest added gym
        fetchLatestGym()

        // Set up window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Function to fetch the latest added gym from Firebase
    private fun fetchLatestGym() {
        database.orderByKey().limitToLast(1).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (gymSnapshot in snapshot.children) {
                    val gym = gymSnapshot.getValue(Gym::class.java)
                    if (gym != null) {
                        // Display gym details in the TextViews
                        gymNameTextView.text = gym.name
                        gymAddressTextView.text = gym.address
                        gymPhoneTextView.text = gym.phone
                        gymEmailTextView.text = gym.email
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })
    }



}