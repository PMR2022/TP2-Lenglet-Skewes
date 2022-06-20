package com.example.marietp1pablo

import com.example.marietp1pablo.model.ListTD

class ProfileListTD(
    private var login: String = "",
    private var lists: MutableList<ListTD> = mutableListOf<ListTD>()
) {
    // CONSTRUCTEURS
    public constructor(lists: MutableList<ListTD>) : this("", lists);

    // GETTERS
    fun getLogin():String {return this.login}
    fun getMesListTD():MutableList<ListTD> {return this.lists;}

    // SETTERS
    fun setLogin(login: String){ this.login = login;}
    fun setMesListsTD(newLists: MutableList<ListTD>){ this.lists = newLists.toMutableList();}

    // FONCTIONS
    fun ajouterListTD(newList: ListTD){this.lists.add(newList)}

    override fun toString(): String { return "ProfileListTD(login='$login', lists=$lists)" }

}