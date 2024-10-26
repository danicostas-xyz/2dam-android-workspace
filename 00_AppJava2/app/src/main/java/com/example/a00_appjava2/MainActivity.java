package com.example.a00_appjava2;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Atrapamos los elementos
        Button b1 = findViewById(R.id.boton1);
        Button b2 = findViewById(R.id.boton2);
        Button b3 = findViewById(R.id.boton3);
        Button b5 = findViewById(R.id.boton5);
        TextView t1 = findViewById(R.id.t1);
        EditText e1 = findViewById(R.id.editText1);

        /*
         * En Android, los eventos son parecidos a JavaScript.
         * Primero tienes que "atrapar" el objeto. En Android hay IoC (no creamos los objetos,
         * Android los crea por nosotros, de la misma manera que un navegador hace con el DOM.
         * Por lo tanto, para atrapar un elemento del "DOM", utilizamos la función findViewById()
         * y le pasamos por parámetro el ID, que en Android está dentro de la clase R.id.boton1
         *
         * Una vez tengas el elemento atrapado en Java, ya puedes usar los eventos asociados al
         * elemento.
         *
         * Dentro de los parámetros del evento, hay que pasarle una clase que implemente la interfaz
         * funcional X. Por ello, se puede pasar por parámetro:
         *
         *   1. Un objeto de una clase que implemente una interfaz funcional.
         *   2. Una clase anónima (e implementar ahí el método a ejecutar por el evento).
         *   3. Una lambda (e implementar ahí el método a ejecutar por el evento).
         *
         *
         * Si quieres ejecutar un evento que es siempre el mismo, quizá lo más interesante sea crear
         * una clase aparte que implemente el método que quieres, así no tienes que volver a progra-
         * marlo
         * */

        // 1. CLASE ANÓNIMA
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // El objeto View v es el que entra en el OnClickListener()
                e1.setText("Botón 1 pulsado");
            }
        });

        // 2. FUNCIÓN LAMBDA
        b2.setOnClickListener((e) -> e1.setText("Botón 2 Pulsado"));

        // 3. PASANDO UN OBJETO QUE IMPLEMENTA LA INTERFAZ FUNCIONAL
        b3.setOnClickListener(new EventoOnClick());

        // ---

        b5.setOnClickListener(view -> {
            String mensaje = e1.getText().toString();
            t1.setText(mensaje);
        });
    }
}