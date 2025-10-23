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

        pizza = rrorintent.getSerializableExtra(MainActivity.PIZZA_KEY) as? Pizza

        initAndPopulateViews()
        buttonEditar = findViewById<Button>(R.id.buttonEditar)

        buttonEditar.setOnClickListener {
            goToEdit()
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
        Log.i("examen1","llegirActivity")
        val i = Intent(this, CreateEditP1Activity::class.java)
        i.putExtra(MainActivity.PIZZA_KEY, pizza) // pizza és Serializable

        startActivity(i)
    }

    private fun initAndPopulateViews(){
        findViewById<TextView>(R.id.textViewNom).text = "Nom: ${pizza.nomRecepta}"
        findViewById<TextView>(R.id.textViewPreu).text = "Preu: ${pizza.preu} €"
        findViewById<TextView>(R.id.textViewUnitats).text = "Unitats: ${pizza.unitats}"
        findViewById<TextView>(R.id.textViewMida).text = "Mida: ${pizza.mida}"
        findViewById<TextView>(R.id.textViewMassa).text = "Massa: ${pizza.tipusMassa}"
        findViewById<TextView>(R.id.textViewTomaquet).text = "Tomàquet: ${if (pizza.isTeTomaquet) "Sí" else "No"}"
        findViewById<TextView>(R.id.textViewMozzarella).text = "Mozzarella: ${if (pizza.isTeMozzarella) "Sí" else "No"}"
        findViewById<TextView>(R.id.textViewPernil).text = "Pernil: ${if (pizza.isTePernil) "Sí" else "No"}"
        findViewById<TextView>(R.id.textViewXampinyons).text = "Xampinyons: ${if (pizza.isTeXampinyons) "Sí" else "No"}"
        findViewById<TextView>(R.id.textViewPinya).text = "Pinya: ${if (pizza.isTePinya) "Sí" else "No"}"
        findViewById<TextView>(R.id.textViewPicant).text = "Picant: ${pizza.picant}"
        findViewById<TextView>(R.id.textViewCodi).text = "Codi: ****" // No mostrem el password [cite: 1315]
        findViewById<TextView>(R.id.textViewPuntuacio).text = "Puntuació: ${pizza.puntuacioClient}"
        findViewById<TextView>(R.id.textViewData).text = "Data: ${pizza.dataComanda}"
        findViewById<TextView>(R.id.textViewPerEmportar).text = "Per emportar: ${if (pizza.isPerEmportar) "Sí" else "No"}"
        findViewById<TextView>(R.id.textViewNivellPicant).text = "Nivell Picant: ${pizza.nivellPicant}"
        findViewById<TextView>(R.id.textViewNotes).text = "Notes: ${pizza.notesComanda}"
    }
}