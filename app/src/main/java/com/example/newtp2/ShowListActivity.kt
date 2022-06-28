package com.example.newtp2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newtp2.adapters.ItemListAdapter
import kotlinx.android.synthetic.main.activity_show_list.*

class ShowListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        var item1 = Item(1,1,"item1 de todo1",false)
        var item2 = Item(2,1,"item2 de todo1",false)
        var item3 = Item(3,2,"item3 de todo2",true)
        var item4 = Item(4,2,"item4 de todo2",false)


        var listOfItems = mutableListOf(item1,item2,item3,item4)

        val adapter = ItemListAdapter(listOfItems)
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(this)

        btnAddItem.setOnClickListener{
            val title = etNewItem.text.toString()
            if (title.isBlank()) {
                Toast.makeText(this, "Text cannot be blank", Toast.LENGTH_SHORT).show()
            }
            else if (repeatedElement(title, listOfItems)) {
                Toast.makeText(this, "This item exists already", Toast.LENGTH_SHORT).show()
            }
            else {
                val todo = Item(13,1,"newItem",false)
                listOfItems.add(todo)
                adapter.notifyItemInserted(listOfItems.size - 1)
                hideSoftKeyboard(it)
            }
        }
    }

    private fun hideSoftKeyboard(view: View) {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun repeatedElement(elementName: String, list: MutableList<Item>): Boolean {
        return list.filter{it.label == elementName}.isNotEmpty()
    }
}

