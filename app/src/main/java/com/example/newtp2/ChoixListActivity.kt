package com.example.newtp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_choix_list.*

class ChoixListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)

        val todolists = mutableListOf("Choix 1", "Choix 2", "Choix 3")

        val adapter = TodolistChoiceAdapter(todolists, this)
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)


        btnAddTodo.setOnClickListener {
            val new_todo = etNewList.text.toString()
            todolists.add(new_todo)
            adapter.notifyItemInserted(todolists.size - 1)
        }
    }
}