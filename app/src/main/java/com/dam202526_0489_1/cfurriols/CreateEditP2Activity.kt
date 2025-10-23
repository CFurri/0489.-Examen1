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

class CreateEditP2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

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
        private lateinit var adapterPuntuacio: ArrayAdapter<Int>

        val deleteConfirmLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // Aquí gestionem la RESPOSTA [cite: 1482]
            if (result.resultCode == Activity.RESULT_OK) {
                // L'usuari ha confirmat "Sí"
                Log.i("examen1", "Eliminació confirmada. Tornant a MainActivity.")

                // Creem un intent per tornar a la pantalla principal
                val intent = Intent(this, MainActivity::class.java)
                // Netegem la pila per no tornar aquí [cite: 1483]
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish() // Tanquem l'activitat actual
            } else {
                // L'usuari ha premut "No" o "Enrere"
                Log.i("examen1", "Eliminació cancel·lada.")
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_edit_p2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i("examen1", "CreateEditP2Activity")

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
        adapterNivellPicant =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionsNivell)
        adapterNivellPicant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNivellPicant.adapter = adapterNivellPicant

        // Spinner Puntuació
        val opcionsPuntuacio = arrayOf(1, 2, 3, 4, 5)
        adapterPuntuacio =
            ArrayAdapter(this, android: R. layout . simple_spinner_item, opcionsPuntuacio)
        adapterPuntuacio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPuntuacio.adapter = adapterPuntuacio
    }

    private fun populateData() {
        // Omplim Radio Picant
        when (pizza.picant) {
            "Sí" -> {
                radioPicantSi.isChecked = true
                spinnerNivellPicant.isVisible = true // Mostrem l'extra [cite: 1320]
            }

            else -> {
                radioPicantNo.isChecked = true
                spinnerNivellPicant.isVisible = false // Amaguem l'extra
            }
        }

        // Seleccionem el nivell de picant
        spinnerNivellPicant.setSelection(adapterNivellPicant.getPosition(pizza.nivellPicant))

        editTextCodi.setText(pizza.codiDescompte)
        spinnerPuntuacio.setSelection(adapterPuntuacio.getPosition(pizza.puntuacioClient))
        editTextNotes.setText(pizza.notesComanda)
        editTextData.setText(pizza.dataComanda)
        checkPerEmportar.isChecked = pizza.isPerEmportar
    }

    private fun initListeners() {
        // Lògica de l'Extra: Mostrar/Amagar l'Spinner de nivell picant [cite: 1320]
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

            // 4. Important: Netegem la pila d'activitats [cite: 1353]
            // Això fa que si premem "enrere" des de LlegirActivity,
            // tornem a MainActivity, no a les pantalles d'edició.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

            startActivity(intent)
            finish() // Tanquem P2
            // Hauríem de tancar P1 també, però amb FLAG_ACTIVITY_CLEAR_TOP n'hi ha prou.
        }
    }

    private fun saveDataToObject() {
        pizza.picant = if (radioPicantSi.isChecked) "Sí" else "No"

        if (pizza.picant == "Sí") {
            pizza.nivellPicant = spinnerNivellPicant.selectedItem.toString()
        } else {
            pizza.nivellPicant = "Cap"
        }

        pizza.codiDescompte = editTextCodi.text.toString()
        pizza.puntuacioClient = spinnerPuntuacio.selectedItem as Int
        pizza.notesComanda = editTextNotes.text.toString()
        pizza.dataComanda = editTextData.text.toString()
        pizza.isPerEmportar = checkPerEmportar.isChecked
    }

    private fun launchDeleteConfirm() {
        Log.i("examen1", "Demanant confirmació per eliminar...")
        val intent = Intent(this, DeleteConfirmActivity::class.java)
        // Llancem l'intent esperant un resultat [cite: 1482]
        deleteConfirmLauncher.launch(intent)
    }
}