package com.example.marietp1pablo.adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marietp1pablo.ChoixListActivity
import com.example.marietp1pablo.R
import com.example.marietp1pablo.model.ItemTD
import com.example.marietp1pablo.model.ListTD


class ItemViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    public val title = itemView.findViewById<TextView>(R.id.list_title)
    val mActivity = itemView.context as Activity

    fun bind(item: ItemTD) = with(itemView){
        title.text=item.getDescription()
    }

}