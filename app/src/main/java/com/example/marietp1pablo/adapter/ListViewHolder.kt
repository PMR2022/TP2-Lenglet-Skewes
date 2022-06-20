package com.example.marietp1pablo.adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marietp1pablo.ChoixListActivity
import com.example.marietp1pablo.R
import com.example.marietp1pablo.model.ListTD


class ListViewHolder(val listView: View) : RecyclerView.ViewHolder(listView) {

    public val title = listView.findViewById<TextView>(R.id.list_title)
    val mActivity = listView.context as Activity

    fun bind(list: ListTD) = with(listView){
        title.text=list.getTitre()
    }

}