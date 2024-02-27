package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BuscarListAdapter(private val emplist: ArrayList<BuscarProducto>) : RecyclerView.Adapter<BuscarListAdapter.MyViewHolder>() {

    // This method creates a new ViewHolder object for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_buscar_recycler_template, parent, false)
        return MyViewHolder(itemView)
    }

    // This method returns the total
    // number of items in the data set
    override fun getItemCount(): Int {
        return emplist.size
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentEmp = emplist[position]
        holder.descp1.text = currentEmp.descp1
        holder.img1.setImageResource(currentEmp.img1)
        holder.descp2.text = currentEmp.descp2
        holder.img2.setImageResource(currentEmp.img2)
    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descp1: TextView = itemView.findViewById(R.id.desc1)
        val img1: ImageView = itemView.findViewById(R.id.img1)
        val descp2: TextView = itemView.findViewById(R.id.desc2)
        val img2: ImageView = itemView.findViewById(R.id.img2)
    }
}
