package com.example.marietp1pablo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marietp1pablo.R
import com.example.marietp1pablo.model.ItemTD
import com.example.marietp1pablo.model.ListTD

class ItemAdapter(private val listOfItems: MutableList<ItemTD>): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent, false)
        return ItemViewHolder(ma_ligne)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(item = listOfItems[position])
    }

    override fun getItemCount(): Int { return listOfItems.size; }

}
