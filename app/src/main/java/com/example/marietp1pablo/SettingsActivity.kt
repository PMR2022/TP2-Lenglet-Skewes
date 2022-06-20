package com.example.marietp1pablo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        // Display Pseudo Name
        val pseudo: String? = intent.getStringExtra("Pseudo")
        val textToPrint : TextView = findViewById(R.id.printPseudo)

        textToPrint.text = "Votre pseudo est le suivant : $pseudo"
        //textToPrint.text = pseudo
        //actionBar!!.title=pseudo
    }


}

