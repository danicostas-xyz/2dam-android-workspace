package com.example.a04_pizzerialogin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        TamanoPizza tamanoPizza = TamanoPizza.UNDEFINED;
        pizza.setTamanoPizza(tamanoPizza);
        ArrayList<String> listaIngredientes = new ArrayList<>();
        pizza.setListaIngredientes(listaIngredientes);
        Usuario user = (Usuario) getIntent().getSerializableExtra(MainActivity.K_USER);

        GestorPizza gePzz = new GestorPizza();

        TextView txVwNombreUsuario = findViewById(R.id.txVwNombreUsuario);
        TextView txVwDireccionUsuario = findViewById(R.id.txVwDireccionUsuario);
        TextView txVwPrecio = findViewById(R.id.txVwPrecio);

        assert user != null;
        txVwNombreUsuario.setText(user.getNombre());
        txVwDireccionUsuario.setText(user.getDireccion());

        RadioGroup rg = findViewById(R.id.radioGroupTamanosPizza);

        rg.setOnCheckedChangeListener((radioGroup, checkedId) -> {

            if(checkedId == R.id.rBtGrande) {
                pizza.setTamanoPizza(TamanoPizza.GRANDE);
            } else if (checkedId == R.id.rBtMediana){
                pizza.setTamanoPizza(TamanoPizza.MEDIANA);
            } else if (checkedId == R.id.rBtPequena){
                pizza.setTamanoPizza(TamanoPizza.PEQUENA);
            }
            gePzz.calcularPrecio(pizza);
            // txVwPrecio.setText(String.valueOf(pizza.getPrecio()) + "€");
            // -> Esto es válido pero dice que es redundante cuando se concatena
            // txVwPrecio.setText(pizza.getPrecio());
            //-> Esto no es válido porque le metemos un int a algo que espera un String
            txVwPrecio.setText(pizza.getPrecio() + "€");
            // -> Aquí es válido porque aunque pasamos un int, al concatenar, se infiere que el int se pasa a String
        });

        CheckBox chBxJamon = findViewById(R.id.chBxJamon);
        CheckBox chBxAceitunas = findViewById(R.id.chBxAceitunas);
        CheckBox chBxPepperoni = findViewById(R.id.chBxPepperoni);
        CheckBox chBxBacon = findViewById(R.id.chBxBacon);

        chBxJamon.setOnClickListener(view -> {
            funcionCheckbox(view, gePzz, txVwPrecio, listaIngredientes, pizza, "Jamón");
        });
        chBxAceitunas.setOnClickListener(view -> {
            funcionCheckbox(view, gePzz, txVwPrecio, listaIngredientes, pizza, "Aceitunas");
        });
        chBxPepperoni.setOnClickListener(view -> {
            funcionCheckbox(view, gePzz, txVwPrecio, listaIngredientes, pizza, "Pepperoni");
        });
        chBxBacon.setOnClickListener(view -> {
            funcionCheckbox(view, gePzz, txVwPrecio, listaIngredientes, pizza, "Bacon");
        });


        Button btPedido = findViewById(R.id.btHacerPedido);
        btPedido.setOnClickListener(view -> {

            if(pizza.getListaIngredientes().isEmpty() || pizza.getTamanoPizza() == TamanoPizza.UNDEFINED) {
                if(pizza.getTamanoPizza() == TamanoPizza.UNDEFINED) {
                    showAlertTamanoPizza();
                }
                if(pizza.getListaIngredientes().isEmpty()) {
                    showAlertIngredientes();
                }
            } else {
                user.setPizza(pizza);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("K_USER", user);
                startActivity(intent);
            }
        });
    }

    private void showAlertIngredientes() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Seleccione almenos un ingrediente");

        // Botón OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Código que se ejecuta al hacer clic en OK
                dialog.dismiss();  // Cerrar la alerta
            }
        });

        // Mostrar la alerta
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertTamanoPizza() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Seleccione un tamaño de pizza");

        // Botón OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Código que se ejecuta al hacer clic en OK
                dialog.dismiss();  // Cerrar la alerta
            }
        });

        // Mostrar la alerta
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void funcionCheckbox(View view, GestorPizza gpzz, TextView txVw3, ArrayList<String> listaIngredientes, Pizza pizza, String ingr) {
        CheckBox checkBox = (CheckBox) view;
        boolean isChecked = checkBox.isChecked();
        if(isChecked) {
            listaIngredientes.add(ingr);
        } else listaIngredientes.remove(ingr);
        gpzz.calcularPrecio(pizza);
        txVw3.setText(pizza.getPrecio() + "€");
    }
}