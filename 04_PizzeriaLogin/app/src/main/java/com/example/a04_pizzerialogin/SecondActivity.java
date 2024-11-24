package com.example.a04_pizzerialogin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a04_pizzerialogin.modelo.entidad.Pizza;
import com.example.a04_pizzerialogin.modelo.entidad.TamanoPizza;
import com.example.a04_pizzerialogin.modelo.entidad.Usuario;
import com.example.a04_pizzerialogin.modelo.negocio.GestorPizza;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    public static final String JAMON = "Jamón";
    public static final String ACEITUNAS = "Aceitunas";
    public static final String PEPPERONI = "Pepperoni";
    public static final String BACON = "Bacon";

    TextView tvPrice;
    RadioGroup rg;
    CheckBox chBxJamon;
    CheckBox chBxAceitunas;
    CheckBox chBxPepperoni;
    CheckBox chBxBacon;
    CheckBox[] arrayCheckbox;
    Button btPedido;
    TextView tvUsername;
    TextView tvUserAddress;

    Pizza pizza;
    TamanoPizza tamanoPizza;
    ArrayList<String> listaIngredientes;
    GestorPizza gePzz;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get Intent Data
        user = (Usuario) getIntent().getSerializableExtra(MainActivity.K_USER);

        // Get Instances
        pizza = new Pizza();
        pizza.setTamanoPizza(TamanoPizza.UNDEFINED);
        listaIngredientes = new ArrayList<>();
        pizza.setListaIngredientes(listaIngredientes);
        gePzz = new GestorPizza();

        // Get Views
        tvUsername = findViewById(R.id.txVwNombreUsuario);
        tvUserAddress = findViewById(R.id.txVwDireccionUsuario);

        // Set User Info
        assert user != null;
        tvUsername.setText(user.getNombre());
        tvUserAddress.setText(user.getDireccion());

    } // onCreate();

    @Override
    protected void onStart() {
        super.onStart();

        // Get Views
        initViews();

        // Set Listeners
        setRadioGroupListener(rg, tvPrice);
        setCheckboxListener(tvPrice, arrayCheckbox);
        setbtPedidoListener(btPedido, user);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showOnRestartAlert();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause()", "Ejecutando el método onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop()", "Ejecutando el método onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
        tvPrice = findViewById(R.id.txVwPrecio);
        rg = findViewById(R.id.radioGroupTamanosPizza);
        chBxJamon = findViewById(R.id.chBxJamon);
        chBxAceitunas = findViewById(R.id.chBxAceitunas);
        chBxPepperoni = findViewById(R.id.chBxPepperoni);
        chBxBacon = findViewById(R.id.chBxBacon);
        btPedido = findViewById(R.id.btHacerPedido);
        arrayCheckbox = new CheckBox[]{chBxJamon, chBxAceitunas, chBxPepperoni, chBxBacon};
    }

    private void setRadioGroupListener(RadioGroup rg, TextView tvPrice) {
        rg.setOnCheckedChangeListener((radioGroup, checkedId) -> {

            if(checkedId == R.id.rBtGrande) {
                pizza.setTamanoPizza(TamanoPizza.GRANDE);
            } else if (checkedId == R.id.rBtMediana){
                pizza.setTamanoPizza(TamanoPizza.MEDIANA);
            } else if (checkedId == R.id.rBtPequena){
                pizza.setTamanoPizza(TamanoPizza.PEQUENA);
            }
            gePzz.calcularPrecio(pizza);
            tvPrice.setText(pizza.getPrecio() + "€");
        });
    }

    private void setbtPedidoListener(Button btPedido, Usuario user) {
        btPedido.setOnClickListener(view -> {
            if (isPedidoValido()) {
                user.setPizza(pizza);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("K_USER", user);
                startActivity(intent);
            }
        });
    }

    private void setCheckboxListener(TextView tvPrice, CheckBox[] arrayCheckbox) {
        View.OnClickListener commonCheckboxListener = view -> {
            CheckBox checkBox = (CheckBox) view;
            boolean isChecked = checkBox.isChecked();
            String ingr = "";

            if (checkBox.getText().equals(JAMON)){
                ingr = JAMON;
            } else if (checkBox.getText().equals(ACEITUNAS)) {
                ingr = ACEITUNAS;
            } else if (checkBox.getText().equals(PEPPERONI)){
                ingr = PEPPERONI;
            } else if (checkBox.getText().equals(BACON)){
                ingr = BACON;
            }

            if (isChecked) {
                listaIngredientes.add(ingr);
            } else listaIngredientes.remove(ingr);

            gePzz.calcularPrecio(pizza);
            tvPrice.setText(pizza.getPrecio() + "€");
        };


        for (CheckBox c : arrayCheckbox) {
            c.setOnClickListener(commonCheckboxListener);
        }
    }

    private boolean isPedidoValido() {
        if (pizza.getTamanoPizza() == TamanoPizza.UNDEFINED) {
            showAlertTamanoPizza();
            return false;
        }
        if (pizza.getListaIngredientes().isEmpty()) {
            showAlertIngredientes();
            return false;
        }
        return true;
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

    private void showOnRestartAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bienvenido de vuelta");
        builder.setMessage("¿Quiere seguir con su pedido?");

        // Botón OK
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Código que se ejecuta al hacer clic en OK
                dialog.dismiss();  // Cerrar la alerta
            }
        });

        builder.setNegativeButton("No",  (dialogInterface, i) -> {
            finish();
            dialogInterface.dismiss();
        });

        // Mostrar la alerta
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*

    * Antes, en lugar de tener un evento común para cada Checkbox, tenía un evento para cada uno.
    * Dentro de cada evento, llamaba a una función con un montón de parámetros para poder ejecutar
    * la lógica del evento. Ahora, se simplifica la acción con un evento común para los Checkbox,
    * llamado commonCheckboxListener que ejecuta la misma acción para todos los Checkbox, validando
    * qué Checkbox ha ejecutado la acción en función del resultado del getText().

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

    public void funcionCheckbox(View view, GestorPizza gpzz, TextView txVw3, ArrayList<String> listaIngredientes, Pizza pizza, String ingr) {
        CheckBox checkBox = (CheckBox) view;
        boolean isChecked = checkBox.isChecked();
        if(isChecked) {
            listaIngredientes.add(ingr);
        } else listaIngredientes.remove(ingr);
        gpzz.calcularPrecio(pizza);
        txVw3.setText(pizza.getPrecio() + "€");
    }

     */
}