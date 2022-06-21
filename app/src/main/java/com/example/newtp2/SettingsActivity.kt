package com.example.newtp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val lastPseudo = intent.getStringExtra("EXTRA_PSEUDO")
        val lastPseudoMsg = "Dernier pseudo utilisé: $lastPseudo"
        tvLastPseudo.text = lastPseudoMsg
    }
}