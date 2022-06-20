package com.example.marietp1pablo

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marietp1pablo.adapter.ListAdapter
import com.example.marietp1pablo.model.ListTD
import com.google.gson.Gson

class ChoixListActivity : AppCompatActivity() {
    private lateinit var addNewList: EditText
    private lateinit var pseudo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)

        // Action Bar
        val actionBar = supportActionBar
        actionBar!!.title="ChoixListActivity"
        actionBar.setDisplayHomeAsUpEnabled(true)


        val bundle: Bundle? = intent.extras
        val pseudo: String? = intent.getStringExtra("Pseudo").toString()
        addNewList = findViewById(R.id.inputNewList)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewLists)

        // get a pseudo(object) by its name
        val sharedPreference =  getSharedPreferences("listPseudos",0)
        var pseudoJson = sharedPreference.getString("$pseudo","defaultName")
        var editor = sharedPreference.edit()
        val gson = Gson()
        val pseudoObject : ProfileListTD = gson.fromJson(pseudoJson, ProfileListTD::class.java)
        pseudoObject.ajouterListTD(ListTD())
        recyclerView.adapter = ListAdapter(listOfLists = pseudoObject.getMesListTD())
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)


        btnAdd.setOnClickListener{
            val listesUser = pseudoObject.getMesListTD()
            var listAlreadyExists: Boolean=false //pour ne pas ajouter une liste qui a le même nom qu'un liste existante

            // Verifions que la liste n'existe n'a pas un nom déjà utilisé :
            for (elt in listesUser){
                if (elt.getTitre() == addNewList.text.toString()){
                    listAlreadyExists=true
                    break
                }
            }

            if ((!listAlreadyExists) && (!addNewList.text.isEmpty())){
                val newList = ListTD(addNewList.text.toString())
                pseudoObject.ajouterListTD(newList)
                pseudoJson = gson.toJson(pseudoObject)
                editor.putString("$pseudo",pseudoJson)
                editor.commit()
                recreate()
            }
        }





        /*
        val inputNewList = findViewById<TextInputEditText>(R.id.inputNewList)

        // Recupérer le profil de l'utilisateur
        val sharedPreferences = getSharedPreferences("listPseudos", 0)
        val pseudo: String? = intent.getStringExtra("Pseudo") // recuperer le pseudo donné par user ds mainActivity

        //inputNewList.text = pseudo

        val pseudoJson :String? = sharedPreferences.getString(pseudo,"")
        val gson = Gson()
        val Profil: ProfileListTD= gson.fromJson(pseudoJson, ProfileListTD::class.java)
        //inputNewList.text = Profil.getMesListTD().toString()
        Profil.ajouterListTD(ListTD("list_test"))
        Profil.ajouterListTD(ListTD("list_test_2"))
        //actionBar!!.title=Profil.getMesListTD().toString()

        // Gestion recyclerview
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerviewLists)
        recyclerview.layoutManager = LinearLayoutManager(this, VERTICAL, false) // this creates a vertical layout Manager

        /* test avec 20 listes
        val data_lists = ArrayList<ListTD>()
        for (i in 1..20) {
            data_lists.add(ListTD("List$i"))
        }*/

        // Ajout de la liste de listes dans le recyclerview
        //val lists_titres = listOf<String>()
        //Profil.getMesListTD()
        //val lists_titres = List<String>()
        val lists_titres: MutableList<Int> = arrayListOf()
        for (elt in Profil.getMesListTD()) {lists_titres.add(elt.getTitre())}
        val adapter = ListAdapter(Profil.getMesListTD()) // This will pass the ArrayList to our Adapter
        recyclerview.adapter = adapter // Setting the Adapter with the recyclerview


        val btnAdd: Button = findViewById(R.id.btnAdd)
        */

    }
}