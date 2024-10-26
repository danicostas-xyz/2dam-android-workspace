package com.example.a01_appminicalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Operaciones op = new Operaciones();

        Button btSuma = findViewById(R.id.btSumar);
        Button btResta = findViewById(R.id.btRestar);
        Button btMulti = findViewById(R.id.btMulti);
        Button btDivid = findViewById(R.id.btDivid);
        Button btPoten = findViewById(R.id.btPoten);

        TextView resultado = findViewById(R.id.campoResultado);

        EditText campo1 = findViewById(R.id.campo1);
        EditText campo2 = findViewById(R.id.campo2);


        btSuma.setOnClickListener(view -> {
            double n1 = Double.parseDouble((validarEmpty(campo1)).getText().toString());
            double n2 = Double.parseDouble((validarEmpty(campo2)).getText().toString());
            resultado.setText(String.valueOf(op.suma(n1, n2)));
        });

        btResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double n1 = Double.parseDouble((validarEmpty(campo1)).getText().toString());
                double n2 = Double.parseDouble((validarEmpty(campo2)).getText().toString());
                resultado.setText(String.valueOf(op.resta(n1, n2)));
            }
        });

        btMulti.setOnClickListener(view -> {
            double n1 = Double.parseDouble((validarEmpty(campo1)).getText().toString());
            double n2 = Double.parseDouble((validarEmpty(campo2)).getText().toString());
            resultado.setText(String.valueOf(op.multiplicacion(n1, n2)));
        });

        btDivid.setOnClickListener(view -> {
            double n1 = Double.parseDouble((validarEmpty(campo1)).getText().toString());
            double n2 = Double.parseDouble((validarEmpty(campo2)).getText().toString());
            resultado.setText(String.valueOf(op.division(n1, n2)));
        });

        btPoten.setOnClickListener(view -> {
            double n1 = Double.parseDouble((validarEmpty(campo1)).getText().toString());
            double n2 = Double.parseDouble((validarEmpty(campo2)).getText().toString());
            resultado.setText(String.valueOf(op.potencia(n1, n2)));
        });

    }

    private EditText validarEmpty (EditText campo) {
        if (campo.getText().toString().isEmpty()){
            campo.setText("0");
        }
        return campo;
    }


}