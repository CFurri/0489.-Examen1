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
        i.putExtra(PIZZA_KEY, plantillaPizza)
        startActivity(i)
    }

    private fun createPlantillaPizza(): Pizza {
        // Creem la instància de la classe Java
        val pizza = Pizza()
        // Establim els valors utilitzant els setters
        pizza.nomRecepta = "Barbacoa Opció B"
        pizza.preu = 14.50
        pizza.unitats = 1
        pizza.mida = "Mitjana"
        pizza.tipusMassa = "Clàssica"
        pizza.isTeTomaquet = true
        pizza.isTeMozzarella = true
        pizza.isTePernil = false
        pizza.isTeXampinyons = false
        pizza.isTePinya = false
        pizza.picant = "No"
        pizza.codiDescompte = "DAM2025"
        pizza.puntuacioClient = 4
        pizza.notesComanda = "Sense ceba, si us plau."
        pizza.dataComanda = "23/10/2025"
        pizza.isPerEmportar = true
        pizza.nivellPicant = "Cap"
        return pizza
    }
}