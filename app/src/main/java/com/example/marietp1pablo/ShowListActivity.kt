package com.example.marietp1pablo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.marietp1pablo.adapter.ItemAdapter
import com.example.marietp1pablo.model.ItemTD

class ShowListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        val sharedPreferences = getSharedPreferences("listPseudos", 0)
        val pseudo: String? = intent.getStringExtra("Pseudo")

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerviewItems)
        recyclerview.layoutManager = LinearLayoutManager(this, VERTICAL, false) // this creates a vertical layout Manager

        val data_items = ArrayList<ItemTD>()
        for (i in 1..20) {
            data_items.add(ItemTD("Item$i"))
        }
        val adapter = ItemAdapter(data_items) // This will pass the ArrayList to our Adapter
        recyclerview.adapter = adapter // Setting the Adapter with the recyclerview


    }
}