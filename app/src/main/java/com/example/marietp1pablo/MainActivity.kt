package com.example.marietp1pablo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("listPseudos", 0)

        val input_pseudo: EditText = findViewById(R.id.autocomplete_pseudo)
        val editText: AutoCompleteTextView = findViewById(R.id.autocomplete_pseudo)
        //val list_sug = arrayOf<String>("Marie", "Pablo", "Nico", sharedPreferences.getStringSet("login", null).toTypedArray())
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sharedPreferences.getStringSet("login", HashSet())!!.toTypedArray())
        editText.setAdapter(adapter)

        val btnOk: Button = findViewById(R.id.buttonOk)
        btnOk.setOnClickListener{
            val nom = input_pseudo.text.toString() // nom entré par l'utilisateur
            val pseudo = ProfileListTD(login = nom) // création d'une instance de ProfileListTD basé sur le nom entré par l'utilisateur
            val gson = Gson()
            var editor = sharedPreferences.edit()
            val pseudoJson = gson.toJson(pseudo) // rendre l'objet pseudo en fromat json

            // Ajouter le pseudo sous format json dans les sharedpreferences
            editor.putString("$nom",pseudoJson)
            editor.apply()

            val intent: Intent  = Intent(this, ChoixListActivity::class.java)
            intent.putExtra("Pseudo", nom)
            startActivity(intent)
        }





    }

    /*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val sharedPreferences = getSharedPreferences("listPseudos", 0)
        val input_pseudo: EditText = findViewById(R.id.autocomplete_pseudo)
        val editText: AutoCompleteTextView = findViewById(R.id.autocomplete_pseudo)
        val nom = input_pseudo.text.toString() // nom entré par l'utilisateur
        val btnPref: MenuItem = menu.findItem(R.id.action_settings)
        btnPref.setOnMenuItemClickListener{
            val intent: Intent  = Intent(this, SettingsActivity::class.java)
            intent.putExtra("Pseudo", nom)
            startActivity(intent)
            true
        }
        // Go to the ChoixListActivity through the OK button

        return true
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val pseudoInput : EditText = findViewById(R.id.autocomplete_pseudo)
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra("Pseudo",   pseudoInput.text.toString())
        startActivity(intent)
        return true
    }

}