package com.dam202526_0489_1.cfurriols

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CreateEditP1Activity : AppCompatActivity() {

    private lateinit var pizza: Pizza

    // Declara tots els controls UI
    private lateinit var editTextNom: EditText
    private lateinit var editTextPreu: EditText
    private lateinit var radioGroupMida: RadioGroup
    private lateinit var radioMidaPetita: RadioButton
    private lateinit var radioMidaMitjana: RadioButton
    private lateinit var radioMidaFamiliar: RadioButton
    private lateinit var spinnerMassa: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_edit_p1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recuperem les dades que arriben
        pizza = intent.getSerializableExtra(MainActivity.PIZZA_KEY) as? Pizza


    }
    private fun initViews() {
        editTextNom = findViewById(R.id.editTextNom)
        editTextPreu = findViewById(R.id.editTextPreu)
        radioGroupMida = findViewById(R.id.radioGroupMida)
        radioMidaPetita = findViewById(R.id.radioMidaPetita)
        radioMidaMitjana = findViewById(R.id.radioMidaMitjana)
        radioMidaFamiliar = findViewById(R.id.radioMidaFamiliar)
        spinnerMassa = findViewById(R.id.spinnerMassa)
    }

    private fun populateData() {
        // Omplim EditTexts
        // Com que la classe és Java, hem de fer servir getters (o la propietat si és pública)
        editTextNom.setText(pizza.nomRecepta)
        editTextPreu.setText(pizza.preu.toString()) // Convertim Double a String

        // Omplim RadioGroup
        when (pizza.mida) {
            "Petita" -> radioMidaPetita.isChecked = true
            "Mitjana" -> radioMidaMitjana.isChecked = true
            "Familiar" -> radioMidaFamiliar.isChecked = true
        }

        // Omplim Spinner
        val opcionsMassa = arrayOf("Fina", "Clàssica", "Gruixuda")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionsMassa)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMassa.adapter = adapter

        // Seleccionem l'opció correcta
        val spinnerPosition = adapter.getPosition(pizza.tipusMassa)
        spinnerMassa.setSelection(spinnerPosition)
    }

}