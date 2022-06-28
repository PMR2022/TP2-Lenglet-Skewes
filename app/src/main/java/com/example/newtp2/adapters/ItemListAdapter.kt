package com.example.newtp2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newtp2.Item
import com.example.newtp2.R
import com.example.newtp2.TodoList
import kotlinx.android.synthetic.main.todo_item.view.*

class ItemListAdapter (
    var listOfItem: MutableList<Item>
) : RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder>() {
    inner class ItemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ItemListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfItem.size
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.itemView.apply {
            tvItem.text = listOfItem[position].label
            cbDone.isChecked = listOfItem[position].checked
        }
        //holder.bind(item = listOfItem[position])
    }

    fun display(items: MutableList<Item>) {
        listOfItem.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item : Item) {
        listOfItem.add(item)
        notifyDataSetChanged()
    }


    /*companion object {
        const val ITEM_ID = 1
    }*/

}
