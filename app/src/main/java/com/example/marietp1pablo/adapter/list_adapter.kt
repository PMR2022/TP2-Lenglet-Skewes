package com.example.marietp1pablo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marietp1pablo.R
import com.example.marietp1pablo.model.ListTD

class ListAdapter(private val listOfLists: MutableList<ListTD>): RecyclerView.Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent, false)
        return ListViewHolder(ma_ligne)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list = listOfLists[position])
    }

    override fun getItemCount(): Int { return listOfLists.size; }

}

