package com.example.a00_appjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton1 = findViewById(R.id.boton1);
        boton1.setOnClickListener(view -> Log.i("MI_TAG", "Botón pulsado "));


        Button boton2 = findViewById(R.id.boton2);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MI_TAG2", "Botón2 pulsado ");

            }
        });

    }
}