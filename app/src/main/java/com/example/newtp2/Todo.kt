package com.example.newtp2

data class Todo (
    val title: String,
    var isChecked: Boolean
    )

data class TodoResponse (
    var success : Int,
    var items: MutableList<Todo>,
    var item : Todo
)