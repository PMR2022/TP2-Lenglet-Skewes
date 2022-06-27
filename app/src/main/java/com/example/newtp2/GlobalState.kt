/*package com.example.newtp2

import android.app.Application
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.preference.PreferenceManager
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class GlobalState : Application() {

    val TAG : String = "PMR"

    fun alerter(s: String?) {
        if (s != null) {
            Log.i(TAG, s)
        }
        val t = Toast.makeText(this, s, Toast.LENGTH_LONG)
        t.show()
    }

    @Throws(IOException::class)
    private fun convertStreamToString(`in`: InputStream?): String {
        return try {
            val reader = BufferedReader(InputStreamReader(`in`))
            val sb = StringBuilder()
            var line: String? = null
            while (reader.readLine().also { line = it } != null) {
                sb.append(
                    """
                           $line
                           
                           """.trimIndent()
                )
            }
            sb.toString()
        } finally {
            try {
                if (`in` != null) {
                    `in`.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    fun requete(qs: String?): String? {
        if (qs != null) {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val urlData = prefs.getString("urlData", "http://10.0.2.2/android_chat/data.php")
            try {
                val url = URL("$urlData?$qs")
                Log.i("PMR", "url utilisée : $url")
                var urlConnection: HttpURLConnection? = null
                urlConnection = url.openConnection() as HttpURLConnection
                var `in`: InputStream? = null
                `in` = BufferedInputStream(urlConnection.inputStream)
                val txtReponse = convertStreamToString(`in`)
                urlConnection.disconnect()
                return txtReponse
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return ""
    }


    fun verifReseau(): Boolean {
        // On vérifie si le réseau est disponible,
        // si oui on change le statut du bouton de connexion
        val cnMngr = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cnMngr.activeNetwork
        var sType = "Aucun réseau détecté"
        var bStatut = false
        if (netInfo != null) {
            val netState = netInfo.state
            if (netState.compareTo(NetworkInfo.State.CONNECTED) == 0) {
                bStatut = true
                val netType = netInfo.type
                when (netType) {
                    ConnectivityManager.TYPE_MOBILE -> sType = "Réseau mobile détecté"
                    ConnectivityManager.TYPE_WIFI -> sType = "Réseau wifi détecté"
                }
            }
        }
        Log.i("PMR", sType)
        return bStatut
    }



}*/