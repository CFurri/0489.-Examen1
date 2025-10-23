package com.dam202526_0489_1.cfurriols

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DeleteConfirmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete_confirm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonSi: Button = findViewById(R.id.buttonSi)
        val buttonNo: Button = findViewById(R.id.buttonNo)

        // Si diu "Sí", retornem RESULT_OK [cite: 1483]
        buttonSi.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish() // Tanca aquesta activitat
        }

        // Si diu "No", retornem RESULT_CANCELED
        buttonNo.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish() // Tanca aquesta activitat
        }
    }
}