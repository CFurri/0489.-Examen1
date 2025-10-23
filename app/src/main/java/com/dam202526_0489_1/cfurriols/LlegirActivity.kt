package com.dam202526_0489_1.cfurriols

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LlegirActivity : AppCompatActivity() {

    private lateinit var pizza : Pizza
    private lateinit var buttonEditar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_llegir)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.i("examen1","LlegirActivity")

        pizza = intent.getSerializableExtra(MainActivity.PIZZA_KEY) as? Pizza

        if (pizza == null) {
            Log.e("examen1", "Error: No s'ha rebut la Pizza.")
            finish() // Si no hi ha pizza, tanca l'activitat
            return
        }

        //Les famoses Views
        val tvNom: TextView = findViewById(R.id.textViewNom)
        val tvPreu: TextView = findViewById(R.id.textViewPreu)
        val tvMida: TextView = findViewById(R.id.textViewMida)
        val tvNotes: TextView = findViewById(R.id.textViewNotes)
            //EN FALTEN!!

        //Les dades de l'objecte Pizza
        tvNom.text = "Nom: ${pizza.nomRecepta}"
        tvPreu.text = "Preu: ${pizza.preu} €"
        tvMida.text = "Mida: ${pizza.mida}"
        tvNotes.text = "Notes: ${pizza.notesComanda}"
        //EN FALTEN!!


        buttonEditar = findViewById<Button>(R.id.buttonEditar)

        buttonEditar.setOnClicklistener{
            goToEdit()
        }


    }

    private fun goToEdit(){
        val intent = Intent(this, CreateEditP1Activity::class.java)
        intent.putExtra(MainActivity.PIZZA_KEY, pizza) // pizza és Serializable

        startActivity(intent)
    }

}