package com.example.mygymapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class gym_list : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gymListAdapter: GymListAdapter
    private lateinit var gymList: ArrayList<Gym>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gym_list)

        // Set up window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize RecyclerView and data
        recyclerView = findViewById(R.id.recyclerViewGymList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        gymList = ArrayList()
        gymListAdapter = GymListAdapter(gymList) { gym ->
            // Handle gym click and redirect to gym_profile
            val intent = Intent(this@gym_list, gym_profile::class.java)
            intent.putExtra("gymId", gym.id)
            startActivity(intent)
        }
        recyclerView.adapter = gymListAdapter

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().getReference("Gym")

        // Fetch the list of gyms from Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gymList.clear()
                for (gymSnapshot in snapshot.children) {
                    val gym = gymSnapshot.getValue(Gym::class.java)
                    gym?.let { gymList.add(it) }
                }
                gymListAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}
