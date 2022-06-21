package com.example.newtp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_show_list.*

class ShowListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        var todoList = mutableListOf(
            Todo("todo 1", false),
            Todo("todo 2", true)
        )

        val adapter = ItemListAdapter(todoList)
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(this)

        btnAddItem.setOnClickListener{
            val title = etNewItem.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
            adapter.notifyItemInserted(todoList.size - 1)
        }

    }
}