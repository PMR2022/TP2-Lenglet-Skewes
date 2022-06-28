package com.example.newtp2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newtp2.adapters.TodolistChoiceAdapter
import com.example.newtp2.data.DataProvider
import kotlinx.android.synthetic.main.activity_choix_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ChoixListActivity : AppCompatActivity() {
    private val data_provider : DataProvider by lazy { DataProvider(this.application) }
    private lateinit var adapter : TodolistChoiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)

        val sharedPref = getSharedPreferences("lastPseudo", Context.MODE_PRIVATE)
        var pseudo = sharedPref.getString("lastPseudo","")

        val sharedPrefHash = getSharedPreferences("LesHash", 0)
        var hash = sharedPrefHash.getString("hash","")
        /*
        var item1 = Item(1,1,"item1 de todo1",false)
        var item2 = Item(2,1,"item2 de todo1",false)
        var item3 = Item(3,2,"item3 de todo2",true)
        var item4 = Item(4,2,"item4 de todo2",false)
        var item5 = Item(5,2,"item3 de todo3",true)
        var item6 = Item(6,2,"item4 de todo3",false)

        var todo1 = TodoList(7,"todo1", mutableListOf(item1,item2))
        var todo2 = TodoList(8,"todo2", mutableListOf(item3,item4))
        var todo3 = TodoList(9,"todo3", mutableListOf(item5,item6))

        val todolists = mutableListOf(todo1,todo2,todo3)*/


        val adapter = TodolistChoiceAdapter(mutableListOf(), this)

        mainActivityScope.launch {
            val todosFromApi = pseudo?.let {
                if (hash != null) {
                    data_provider.getListes(hash)
                }
            }
            Log.i("Listes", "todosFromApi.toString()")
            Log.d("Listes", todosFromApi.toString())
            val listOfTodos : MutableList<TodoList> = listOf(todosFromApi) as MutableList<TodoList>
            adapter.display(listOfTodos)
            Log.d("Listes", listOfTodos.toString())
        }

        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)


        btnAddTodo.setOnClickListener {
            val new_todo = TodoList(18,etNewList.text.toString(), mutableListOf())
            if (new_todo.label.isBlank()) {
                Toast.makeText(this, "Text cannot be blank", Toast.LENGTH_SHORT).show()
            }
            else if (repeatedElement(new_todo, adapter.todolists)) {
                Toast.makeText(this, "This todolist exists already", Toast.LENGTH_SHORT).show()
            }
            else {
                adapter.todolists.add(new_todo)
                adapter.notifyItemInserted(adapter.todolists.size - 1)
                hideSoftKeyboard(it)
            }
        }
    }

    private val mainActivityScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    private fun hideSoftKeyboard(view: View) {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun repeatedElement(elementName: TodoList, list: MutableList<TodoList>): Boolean {
        return list.filter{it == elementName}.isNotEmpty()
    }
}

