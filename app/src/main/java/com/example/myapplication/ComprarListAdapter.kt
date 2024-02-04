package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComprarListAdapter(private val emplist: ArrayList<Producto_Largo>) : RecyclerView.Adapter<ComprarListAdapter.MyViewHolder>() {

        // This method creates a new ViewHolder object for each item in the RecyclerView
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                // Inflate the layout for each item and return a new ViewHolder object
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_comprar_recycler_template, parent, false)
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
                holder.descp.text = currentEmp.descp
                holder.imagen.setImageResource(currentEmp.imagen)
        }

        // This class defines the ViewHolder object for each item in the RecyclerView
        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val descp: TextView = itemView.findViewById(R.id.textComprar)
                val imagen: ImageView = itemView.findViewById(R.id.imageComprar)
        }
}
