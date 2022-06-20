package com.example.marietp1pablo.model

class ItemTD(
    private var description: String = "",
    private var fait: Boolean = false,
    private var list: ListTD = ListTD()
) {
    // CONSTRUCTEURS
    constructor(desc: String) : this(desc, false);

    // GETTERS
    fun getDescription():String {return this.description;}
    fun getFait():Boolean {return this.fait;}

    // SETTERS
    fun setDescription(desc: String){ this.description = desc;}
    fun setFait(fait: Boolean ) { this.fait = fait;}

    // FONCTIONS
    override fun toString(): String { return "ItemTD(description='$description', fait=$fait)"
    }

}