package com.example.newtp2

class Item (
    var id: Int,
    var idList : Int,
    var label: String,
    var checked: Boolean,
)

class ItemResponse (
    var sucess : Int,
    var items: MutableList<Item>,
    var item : Item
)