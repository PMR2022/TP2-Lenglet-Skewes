package com.example.newtp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.newtp2.data.Autentification
import com.example.newtp2.data.DataProvider
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import com.google.android.gms.common.api.Api as Api

class MainActivity : AppCompatActivity() {
    private val api : DataProvider by lazy { DataProvider(this.application) }
    private lateinit var sharedPrefHash : SharedPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("lastPseudo", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val sharedPref_settings = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor_pref = sharedPref_settings.edit()

        val sharedPrefHash = getSharedPreferences("LesHash", 0)

        val lastPseudo = sharedPref.getString("lastPseudo", null)
        etPseudo.setText(lastPseudo)

        Log.i("MainActivity","coucou1")

        btnOk.setOnClickListener{
            Log.i("MainActivity","coucou2")
            val pseudo = etPseudo.text.toString()
            val password = etPassword.text.toString()
            var hash = sharedPrefHash.getString("hash","")
            editor.apply{
                putString("lastPseudo", pseudo)
                apply()
            }
            editor_pref.apply{
                putString("login",pseudo)
                putString("passe", password)
                apply()
            }
            Log.i("MainActivity","coucou3")
            Toast.makeText(this, "Pseudo $pseudo saved in Shared Preferences", Toast.LENGTH_SHORT).show()
            Intent(this, ChoixListActivity::class.java).also {
                startActivity(it)
            }
            //authenticate(pseudo, password, this)
            Log.i("MainActivity","end of the click listener")
        }
    }

    /*
    private val mainActivityScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )*/
/*
    private fun authenticate(name: String, password: String, context: Context) {
        mainActivityScope.launch {
            kotlin.runCatching {
                callHash(name, password)
            }.fold(
                onSuccess = {  hashReturn : Any? ->
                    var hash : String
                    if (hashReturn is hash?) {
                        // Cas qu'il est dans mode online
                        hash = hashReturn as hash
                    }
                    val gson = Gson()
                    val hashJson = gson.toJson(hash)
                    sharedPrefHash.edit().putString("hash", hashJson).commit()
                    Log.d("hash", "hashJson $hashJson")
                    Log.e("State", "Success authenticate")
                    var intent : Intent = Intent(context, ChoixListActivity::class.java)
                    intent.putExtra("Pseudo", name)
                    startActivity(intent)
                },
                onFailure = {
                    Log.e("State", "Failure authenticate")
                    var intent : Intent = Intent(context, ChoixListActivity::class.java)
                    intent.putExtra("Pseudo", name)
                    startActivity(intent)
                }
            )
        }
    }

    private suspend fun callHash(name: String, password: String): String {
        return api.autentification(name, password)
    }*/



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miPreferences -> {
                val pseudo = etPseudo.text.toString()
                val password = etPassword.text.toString()
                Intent(this, SettingsActivity::class.java).also {
                    it.putExtra("EXTRA_PSEUDO", pseudo)
                    it.putExtra("PASSWORD", password)
                    startActivity(it)
                }
            }
        }
        return true
    }

}
