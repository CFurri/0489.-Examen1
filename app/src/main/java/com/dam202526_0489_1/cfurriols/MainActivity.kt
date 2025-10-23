package com.dam202526_0489_1.cfurriols

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        const val PIZZA_KEY = "PIZZA_KEY"
    }
    private lateinit var plantillaPizza : Pizza

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i("examen1", "MainActivity")

        //Inicialitzant Views...
        var btnLlegir = findViewById<Button>(R.id.buttonLlegir)
    }

    fun goToRead(btnLlegir : View) {
        Log.i("examen1", "MainActivity")
        val i = Intent(MainActivity@ this, LlegirActivity::class.java)
        i.putExtra(PIZZA_KEY, Pizza)
        startActivity(i)
    }

    private fun plantillaPizza(): Pizza{
        return Pizza(
            nomRecepta = "Barbacoa Opció B",
            preu = 14.50,
            unitats = 1,
            mida = "Mitjana",
            tipusMassa = "Clàssica",
            teTomaquet = true,
            teMozzarella = true,
            tePernil = false,
            teXampinyons = false,
            tePinya = false,
            picant = "No",
            codiDescompte = "DAM2025",
            puntuacioClient = 4,
            notesComanda = "Sense ceba, si us plau.",
            dataComanda = "23/10/2025",
            perEmportar = true,
            nivellPicant = "Cap"
        )
    }
}