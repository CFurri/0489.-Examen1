package com.dam202526_0489_1.cfurriols

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.result.contract.ActivityResultContracts
import android.app.Activity
import androidx.core.view.isVisible

class CreateEditP2Activity : AppCompatActivity() {
    private lateinit var pizza: Pizza

    // Controls UI
    private lateinit var radioGroupPicant: RadioGroup
    private lateinit var radioPicantNo: RadioButton
    private lateinit var radioPicantSi: RadioButton
    private lateinit var spinnerNivellPicant: Spinner
    private lateinit var editTextCodi: EditText
    private lateinit var spinnerPuntuacio: Spinner
    private lateinit var editTextNotes: EditText
    private lateinit var editTextData: EditText
    private lateinit var checkPerEmportar: CheckBox
    private lateinit var buttonGuardar: Button
    private lateinit var buttonEliminarP2: Button

    // Adapters pels Spinners
    private lateinit var adapterNivellPicant: ArrayAdapter<String>
    private lateinit var adapterPuntuacio: ArrayAdapter<String>

    // Launcher per Eliminar (igual que a P1)
    private val deleteConfirmLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_edit_p2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i("examen1", "CreateEditP2Activity")
        pizza = intent.getSerializableExtra(MainActivity.PIZZA_KEY) as Pizza

        buttonEliminarP2.setOnClickListener {
            launchDeleteConfirm()
        }

        initViews()
        initSpinners()
        populateData()
        initListeners()
    }


    private fun initViews() {
        radioGroupPicant = findViewById(R.id.radioGroupPicant)
        radioPicantNo = findViewById(R.id.radioPicantNo)
        radioPicantSi = findViewById(R.id.radioPicantSi)
        spinnerNivellPicant = findViewById(R.id.spinnerNivellPicant)
        editTextCodi = findViewById(R.id.editTextCodi)
        spinnerPuntuacio = findViewById(R.id.spinnerPuntuacio)
        editTextNotes = findViewById(R.id.editTextNotes)
        editTextData = findViewById(R.id.editTextData)
        checkPerEmportar = findViewById(R.id.checkPerEmportar)
        buttonGuardar = findViewById(R.id.buttonGuardar)
        buttonEliminarP2 = findViewById(R.id.buttonEliminarP2)
    }

    private fun initSpinners() {
        // Spinner Nivell Picant
        val opcionsNivell = arrayOf("Suau", "Fort", "Infernal")
        adapterNivellPicant = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionsNivell)
        adapterNivellPicant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNivellPicant.adapter = adapterNivellPicant

        // Spinner Puntuació
        val opcionsPuntuacio = arrayOf("1", "2", "3", "4", "5")
        adapterPuntuacio = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionsPuntuacio)
        adapterPuntuacio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPuntuacio.adapter = adapterPuntuacio
    }

    private fun populateData() {
        // Omplim Radio Picant
        when (pizza.picant) {
            "Sí" -> {
                radioPicantSi.isChecked = true
                spinnerNivellPicant.isVisible = true // Mostrem l'extra
            }
            else -> {
                radioPicantNo.isChecked = true
                spinnerNivellPicant.isVisible = false // Amaguem l'extra
            }
        }

        // Seleccionem el nivell de picant
        spinnerNivellPicant.setSelection(adapterNivellPicant.getPosition(pizza.nivellPicant))

        editTextCodi.setText(pizza.codiDescompte)
        spinnerPuntuacio.setSelection(adapterPuntuacio.getPosition(pizza.puntuacioClient.toString()))
        editTextNotes.setText(pizza.notesComanda)
        editTextData.setText(pizza.dataComanda)
        checkPerEmportar.isChecked = pizza.isPerEmportar
    }

    private fun initListeners() {
        // Lògica de l'Extra: Mostrar/Amagar l'Spinner de nivell picant
        radioGroupPicant.setOnCheckedChangeListener { _, checkedId ->
            spinnerNivellPicant.isVisible = (checkedId == R.id.radioPicantSi)
        }

        // Lògica del botó GUARDAR
        buttonGuardar.setOnClickListener {
            Log.i("examen1", "CreateEditP2Activity:Guardant dades...")
            // 1. Desar dades d'aquesta pantalla a l'objecte
            saveDataToObject()

            // 2. Log final obligatori
            Log.i("examen1", "Objecte Pizza Final: ${pizza.toString()}")

            // 3. Tornar a LlegirActivity amb la pizza actualitzada
            val intent = Intent(this, LlegirActivity::class.java)
            intent.putExtra(MainActivity.PIZZA_KEY, pizza)

            // 4. Important: Netegem la pila d'activitats
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

            startActivity(intent)
            finish() // Tanquem P2
        }

        // Lògica del botó Eliminar
        buttonEliminarP2.setOnClickListener {
            launchDeleteConfirm()
        }
    }

    /**
     * Inicia l'activitat de confirmació d'esborrat.
     */
    private fun launchDeleteConfirm() {
        Log.i("examen1", "Demanant confirmació per eliminar...")
        val intent = Intent(this, DeleteConfirmActivity::class.java)
        // Llancem l'intent esperant un resultat
        deleteConfirmLauncher.launch(intent)
    }

    private fun saveDataToObject() {
        pizza.picant = if (radioPicantSi.isChecked) "Sí" else "No"

        if (pizza.picant == "Sí") {
            pizza.nivellPicant = spinnerNivellPicant.selectedItem.toString()
        } else {
            pizza.nivellPicant = "Cap"
        }

        pizza.codiDescompte = editTextCodi.text.toString()
        pizza.puntuacioClient = (spinnerPuntuacio.selectedItem as String).toIntOrNull() ?: 0
        pizza.notesComanda = editTextNotes.text.toString()
        pizza.dataComanda = editTextData.text.toString()
        pizza.isPerEmportar = checkPerEmportar.isChecked
    }
}