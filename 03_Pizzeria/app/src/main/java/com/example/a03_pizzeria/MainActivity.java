package com.example.a03_pizzeria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int precioTamanho;
    public static int precioIngredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btCalcularPrecio = findViewById(R.id.btCalculaPrecio);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        CheckBox cbPepperoni = findViewById(R.id.cbPepperoni);
        CheckBox cbJamon = findViewById(R.id.cbJamon);
        CheckBox cbAceitunas = findViewById(R.id.cbAceitunas);
        CheckBox cbBacon = findViewById(R.id.cbBacon);

        TextView tvPrecio = findViewById(R.id.tvPrecio);

        radioGroup.setOnCheckedChangeListener((radioGroup1, checkedId) -> {

            if (checkedId == R.id.rbGrande) {
                precioTamanho = 15;
            } else if (checkedId == R.id.rbMediana) {
                precioTamanho = 10;
            } else if (checkedId == R.id.rbPeque) {
                precioTamanho = 5;
            }

            calcularPrecioTotal(tvPrecio);
        });

        cbPepperoni.setOnClickListener(new PrecioIngredientes(this));
        cbJamon.setOnClickListener(new PrecioIngredientes(this));
        cbAceitunas.setOnClickListener(new PrecioIngredientes(this));
        cbBacon.setOnClickListener(new PrecioIngredientes(this));


        btCalcularPrecio.setOnClickListener(view -> {
            calcularPrecioTotal(tvPrecio);
        });

    }
    public void calcularPrecioTotal(TextView tvPrecio) {
        int precioTotal = precioTamanho + precioIngredientes;
        tvPrecio.setText(precioTotal + "€");
    }

}