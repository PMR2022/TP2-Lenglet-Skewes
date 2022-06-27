package com.example.newtp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("lastPseudo", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val sharedPref_settings = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor_pref = sharedPref_settings.edit()

        val lastPseudo = sharedPref.getString("lastPseudo", null)
        etPseudo.setText(lastPseudo)

        btnOk.setOnClickListener{
            val pseudo = etPseudo.text.toString()
            val password = etPassword.text.toString()
            var canLogin: Boolean

            lifecycleScope.launchWhenCreated {
                val response = try {
                    RetrofitInstance.api.getUsers()
                } catch (e: IOException) {
                    Log.e("IOE Exception", "Internet Error (maybe)")
                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Log.e("HttpException", "Unexpected response")
                    return@launchWhenCreated
                }
                if (response.isSuccessful && response.body() != null   ) {
                    canLogin = true
                    Log.e("Response", "Body: ${response.body()}")
                } else {
                    canLogin = false
                    Log.e("Response", "Response not successful")
                }
                if (!canLogin) {
                    Toast.makeText(this@MainActivity, "Can't login", Toast.LENGTH_LONG).show()
                } else {
                    editor.apply{
                        putString("lastPseudo", pseudo)
                        apply()
                    }
                    editor_pref.apply{
                        putString("login",pseudo)
                        putString("passe", password)
                        apply()
                    }
                    Toast.makeText(this@MainActivity, "Pseudo $pseudo saved in Shared Preferences", Toast.LENGTH_SHORT).show()
                    Intent(this@MainActivity, ChoixListActivity::class.java).also {
                        startActivity(it)
                    }
                }
            }

            // si username es unico y está bien loggeado
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
