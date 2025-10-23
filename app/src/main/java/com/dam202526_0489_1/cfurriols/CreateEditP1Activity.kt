package com.dam202526_0489_1.cfurriols

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.result.contract.ActivityResultContracts
import android.app.Activity
import android.content.Intent
import android.view.View

class CreateEditP1Activity : AppCompatActivity() {

    private lateinit var pizza: Pizza

    // Declara tots els controls UI
    private lateinit var editTextNom: EditText
    private lateinit var editTextPreu: EditText
    private lateinit var editTextUnitats: EditText
    private lateinit var radioGroupMida: RadioGroup
    private lateinit var radioMidaPetita: RadioButton
    private lateinit var radioMidaMitjana: RadioButton
    private lateinit var radioMidaFamiliar: RadioButton
    private lateinit var spinnerMassa: Spinner
    private lateinit var checkTomaquet: CheckBox
    private lateinit var checkMozzarella: CheckBox
    private lateinit var checkPernil: CheckBox
    private lateinit var checkXampinyons: CheckBox
    private lateinit var togglePinya: ToggleButton
    private lateinit var buttonSeguent: Button
    private lateinit var buttonEliminarP1: Button

    val deleteConfirmLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Aquí gestionem la RESPOSTA
        if (result.resultCode == Activity.RESULT_OK) {
            // L'usuari ha confirmat "Sí"

            // Creem un intent per tornar a la pantalla principal
            val intent = Intent(this, MainActivity::class.java)
            // Netegem la pila per no tornar aquí
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish() // Tanquem l'activitat actual
        } else {
            // L'usuari ha premut "No" o "Enrere"
            Log.i("examen1", "Eliminació cancel·lada.")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_edit_p1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i("examen1","CreateEditP1Activity")

        //Recuperem les dades que arriben
        pizza = intent.getSerializableExtra(MainActivity.PIZZA_KEY) as Pizza

        if (pizza == null) {

            return
        }

        initViews()
        populateData()

        buttonSeguent.setOnClickListener {
            // 1. Desar dades d'aquesta pantalla a l'objecte 'Pizza'
            saveDataToObject()

            val i = Intent(CreateEditP1Activity@this, CreateEditP2Activity::class.java)
            i.putExtra(MainActivity.PIZZA_KEY, pizza)
            startActivity(i)
        }

        buttonEliminarP1.setOnClickListener {
            launchDeleteConfirm()
        }
    }



    private fun launchDeleteConfirm() {
        Log.i("examen1", "Demanant confirmació per eliminar...")
        val intent = Intent(this, DeleteConfirmActivity::class.java)
        // Llancem l'intent esperant un resultat
        deleteConfirmLauncher.launch(intent)
    }

    private fun initViews() {
        editTextNom = findViewById(R.id.editTextNom)
        editTextPreu = findViewById(R.id.editTextPreu)
        editTextUnitats = findViewById(R.id.editTextUnitats)
        radioGroupMida = findViewById(R.id.radioGroupMida)
        radioMidaPetita = findViewById(R.id.radioMidaPetita)
        radioMidaMitjana = findViewById(R.id.radioMidaMitjana)
        radioMidaFamiliar = findViewById(R.id.radioMidaFamiliar)
        spinnerMassa = findViewById(R.id.spinnerMassa)
        checkTomaquet = findViewById(R.id.checkTomaquet)
        checkMozzarella = findViewById(R.id.checkMozzarella)
        checkPernil = findViewById(R.id.checkPernil)
        checkXampinyons = findViewById(R.id.checkXampinyons)
        togglePinya = findViewById(R.id.togglePinya)
        buttonSeguent = findViewById(R.id.buttonSeguent)
        buttonEliminarP1 = findViewById(R.id.buttonEliminarP1)
    }

    private fun populateData() {
        // Omplim EditTexts
        editTextNom.setText(pizza.nomRecepta)
        editTextPreu.setText(pizza.preu.toString())
        editTextUnitats.setText(pizza.unitats.toString())

        // Omplim RadioGroup
        when (pizza.mida) {
            "Petita" -> radioMidaPetita.isChecked = true
            "Mitjana" -> radioMidaMitjana.isChecked = true
            "Familiar" -> radioMidaFamiliar.isChecked = true
        }

        // Omplim Spinner Massa
        val opcionsMassa = arrayOf("Fina", "Clàssica", "Gruixuda")
        val adapterMassa = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionsMassa)
        adapterMassa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMassa.adapter = adapterMassa
        spinnerMassa.setSelection(adapterMassa.getPosition(pizza.tipusMassa))

        // Omplim CheckBoxes i Toggle
        checkTomaquet.isChecked = pizza.isTeTomaquet
        checkMozzarella.isChecked = pizza.isTeMozzarella
        checkPernil.isChecked = pizza.isTePernil
        checkXampinyons.isChecked = pizza.isTeXampinyons
        togglePinya.isChecked = pizza.isTePinya
    }

    private fun saveDataToObject() {
        pizza.nomRecepta = editTextNom.text.toString()
        pizza.preu = editTextPreu.text.toString().toDoubleOrNull() ?: 0.0
        pizza.unitats = editTextUnitats.text.toString().toIntOrNull() ?: 0

        pizza.mida = when (radioGroupMida.checkedRadioButtonId) {
            R.id.radioMidaPetita -> "Petita"
            R.id.radioMidaMitjana -> "Mitjana"
            R.id.radioMidaFamiliar -> "Familiar"
            else -> ""
        }

        pizza.tipusMassa = spinnerMassa.selectedItem.toString()

        pizza.isTeTomaquet = checkTomaquet.isChecked
        pizza.isTeMozzarella = checkMozzarella.isChecked
        pizza.isTePernil = checkPernil.isChecked
        pizza.isTeXampinyons = checkXampinyons.isChecked
        pizza.isTePinya = togglePinya.isChecked
    }
}