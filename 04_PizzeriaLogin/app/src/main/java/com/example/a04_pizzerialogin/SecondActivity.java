package com.example.a04_pizzerialogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a04_pizzerialogin.modelo.entidad.Pizza;
import com.example.a04_pizzerialogin.modelo.entidad.TamanoPizza;
import com.example.a04_pizzerialogin.modelo.entidad.Usuario;
import com.example.a04_pizzerialogin.modelo.negocio.GestorPizza;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Pizza pizza = new Pizza();
        ArrayList<String> listaIngredientes = new ArrayList<>();
        pizza.setListaIngredientes(listaIngredientes);

        Usuario user = (Usuario) getIntent().getSerializableExtra(MainActivity.K_USER);

        GestorPizza gpzz = new GestorPizza();
        TextView txVw1 = findViewById(R.id.txVw1);
        TextView txVw2 = findViewById(R.id.txVw2);
        TextView txVw3 = findViewById(R.id.txVwPrecio);


        txVw1.setText(user.getNombre());
        txVw2.setText(user.getDireccion());





        RadioGroup rg = findViewById(R.id.radioGroup);

        CheckBox chBx1 = findViewById(R.id.chBx1);
        CheckBox chBx2 = findViewById(R.id.chBx2);
        CheckBox chBx3 = findViewById(R.id.chBx3);
        CheckBox chBx4 = findViewById(R.id.chBx4);

        rg.setOnCheckedChangeListener((radioGroup, checkedId) -> {

            if(checkedId == R.id.rBt1) {
                pizza.setTamanoPizza(TamanoPizza.GRANDE);
            } else if (checkedId == R.id.rBt2){
                pizza.setTamanoPizza(TamanoPizza.MEDIANA);
            } else if (checkedId == R.id.rBt3){
                pizza.setTamanoPizza(TamanoPizza.PEQUENA);
            }
            gpzz.calcularPrecio(pizza);
            txVw3.setText(String.valueOf(pizza.getPrecio()));
        });

        chBx1.setOnClickListener(view -> {
            vaina(view, gpzz, txVw3, listaIngredientes, pizza);
        });
        chBx2.setOnClickListener(view -> {
            vaina(view, gpzz, txVw3, listaIngredientes, pizza);
        });
        chBx3.setOnClickListener(view -> {
            vaina(view, gpzz, txVw3, listaIngredientes, pizza);
        });
        chBx4.setOnClickListener(view -> {
            vaina(view, gpzz, txVw3, listaIngredientes, pizza);
        });


        Button btPedido = findViewById(R.id.bt2);

        btPedido.setOnClickListener(view -> {
            user.setPizza(pizza);

            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

            intent.putExtra("K_USER", user);
            startActivity(intent);

        });

    }

    public void vaina (View view, GestorPizza gpzz, TextView txVw3, ArrayList<String> listaIngredientes, Pizza pizza) {
        CheckBox checkBox = (CheckBox) view;
        boolean isChecked = checkBox.isChecked();
        if(isChecked) {
            listaIngredientes.add("ing");
        } else listaIngredientes.remove("ing");
        gpzz.calcularPrecio(pizza);
        txVw3.setText(String.valueOf(pizza.getPrecio()));
    }
}