package com.example.newtp2

class Item (
    var idList : Int,
    var id: Int,
    var label: String,
    var checked: Boolean,
)

class ItemResponse (
    var sucess : Int,
    var items: MutableList<Item>,
    var item : Item
)