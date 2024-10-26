package com.example.a00_appkotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton1: Button = findViewById(R.id.boton1)
        val boton2 = findViewById<Button>(R.id.boton2) // Hay que pasarle en el operador diamante el tipo de dato, porque estamos inicializando la variable con inferencia, sin declarar el tipo

        boton2.setOnClickListener {Log.i("tagg", "menasje")}





    }
}