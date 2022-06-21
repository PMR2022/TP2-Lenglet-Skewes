package com.example.newtp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("lastPseudo", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val lastPseudo = sharedPref.getString("lastPseudo", null)
        etPseudo.setText(lastPseudo)

        btnOk.setOnClickListener{
            val pseudo = etPseudo.text.toString()
            editor.apply{
                putString("lastPseudo", pseudo)
                apply()
            }
            Toast.makeText(this, "Pseudo $pseudo saved in Shared Preferences", Toast.LENGTH_SHORT).show()
            Intent(this, ChoixListActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miPreferences -> {
                val pseudo = etPseudo.text.toString()
                Intent(this, SettingsActivity::class.java).also {
                    it.putExtra("EXTRA_PSEUDO", pseudo)
                    startActivity(it)
                }
            }
        }
        return true
    }

}

