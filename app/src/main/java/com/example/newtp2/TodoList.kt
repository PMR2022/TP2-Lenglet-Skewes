package com.example.newtp2

data class TodoList (
    var id: Int,
    var label: String,
    val itemList: MutableList<Item> = mutableListOf<Item>()
    )

data class TodoListResponse (
    var success : Int,
    var lists: MutableList<TodoList>,
)