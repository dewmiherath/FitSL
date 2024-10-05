package com.example.mygymapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GymListAdapter(
    private val gymList: ArrayList<Gym>,
    private val onGymClick: (Gym) -> Unit
) : RecyclerView.Adapter<GymListAdapter.GymViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GymViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.gym_list_item, parent, false)
        return GymViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GymViewHolder, position: Int) {
        val gym = gymList[position]
        holder.gymNameTextView.text = gym.name
        holder.gymAddressTextView.text = gym.address

        holder.itemView.setOnClickListener {
            onGymClick(gym)
        }
    }

    override fun getItemCount(): Int {
        return gymList.size
    }

    class GymViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gymNameTextView: TextView = itemView.findViewById(R.id.textViewGymName)
        val gymAddressTextView: TextView = itemView.findViewById(R.id.textViewGymAddress)
    }
}
