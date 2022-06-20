package com.example.marietp1pablo.model

import android.provider.ContactsContract
import com.example.marietp1pablo.ProfileListTD

class ListTD(
    private var titre: String = "",
    private var lesItems: MutableList<ItemTD> = mutableListOf<ItemTD>(),
    private var owner: ProfileListTD = ProfileListTD()
) {
    // CONSTRUCTEURS
    constructor(titre: String) : this(titre, mutableListOf<ItemTD>());
    constructor(titre: String, owner: ProfileListTD) : this(titre, mutableListOf<ItemTD>(), owner);

    // GETTERS
    fun getTitre():String {return this.titre;}
    fun getMesItemTD():MutableList<ItemTD> {return this.lesItems;}
    fun getOwner(): ProfileListTD {return this.owner}

    // SETTERS
    fun setTitre(titre: String){ this.titre = titre;}
    fun setMesItemTD(newItems: MutableList<ItemTD>) { this.lesItems = newItems.toMutableList();}
    fun setOwner(owner: ProfileListTD) {this.owner = owner}

    // FONCTIONS
    fun ajouterItemTD(newItem: ItemTD) {this.lesItems.add(newItem);}
    fun rechercherItem(descriptionItem: String): ItemTD? {return lesItems.find { it.getDescription()==descriptionItem};}

    override fun toString(): String { return "ListTD(titre='$titre', lesItems=$lesItems)" }

}