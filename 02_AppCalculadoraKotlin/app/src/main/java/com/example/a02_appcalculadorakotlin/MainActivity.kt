package com.example.a02_appcalculadorakotlin

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val operaciones = Operaciones()

        val btSumar = findViewById<Button>(R.id.btSumar)
        val btRestar: Button = findViewById(R.id.btRestar)
        val btMulti = findViewById<Button>(R.id.btMulti)
        val btDivid = findViewById<Button>(R.id.btDivid)
        val btPoten = findViewById<Button>(R.id.btPoten)

        val campo1 = findViewById<EditText>(R.id.campo1)
        val campo2 = findViewById<EditText>(R.id.campo2)
        val campoResultado = findViewById<TextView>(R.id.campoResultado)

        btSumar.setOnClickListener {

            var n1 = validarEmpty(campo1).text.toString().toDouble()
            var n2 = validarEmpty(campo2).text.toString().toDouble()

            campoResultado.text = (n1 + n2).toString()
        }

        btRestar.setOnClickListener {

            var n1 = validarEmpty(campo1).text.toString().toDouble()
            var n2 = validarEmpty(campo2).text.toString().toDouble()

            campoResultado.text = (n1 - n2).toString()
        }

        btMulti.setOnClickListener {

            var n1 = validarEmpty(campo1).text.toString().toDouble()
            var n2 = validarEmpty(campo2).text.toString().toDouble()

            campoResultado.text = (n1 * n2).toString()
        }

        btDivid.setOnClickListener {

            var n1 = validarEmpty(campo1).text.toString().toDouble()
            var n2 = validarEmpty(campo2).text.toString().toDouble()

            if (n2.toInt() == 0) {
                val builder = AlertDialog.Builder(this) // 'this' es el contexto (puede ser Activity o Fragment)
                builder.setTitle("Error")
                builder.setMessage("No puedes dividir entre 0.")
                builder.setPositiveButton("Aceptar") { dialog, which ->
                    // Acción cuando el usuario pulsa "Aceptar"
                    Toast.makeText(applicationContext, "Has aceptado", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Cancelar") { dialog, which ->
                    // Acción cuando el usuario pulsa "Cancelar"
                    dialog.dismiss() // Cierra el diálogo
                }

                // Mostrar la alerta
                builder.show()
            } else {
                campoResultado.text = (n1 / n2).toString()
            }

        }

        btPoten.setOnClickListener {

            var n1 = validarEmpty(campo1).text.toString().toDouble()
            var n2 = validarEmpty(campo2).text.toString().toDouble()

            campoResultado.text = operaciones.potencia(n1, n2).toString()
        }
    }

    fun validarEmpty(campo: EditText): EditText {

        if (campo.text.toString().isEmpty()) {
            campo.setText(0)
        }

        return campo
    }

}