package com.example.a04_pizzerialogin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a04_pizzerialogin.modelo.entidad.Usuario;

public class ThirdActivity extends AppCompatActivity {

    public static final String ESTADO_PEDIDO = "ESTADO_PEDIDO";

    boolean estadoPedido;
    String mensajeEstadoPedido;

    TextView tvUsername;
    TextView tvUserAddress;
    TextView tvTamanoPizza;
    TextView tvListaIngredientes;
    TextView tvPrice;
    TextView tvEstadoPedido;

    Usuario user;
    Button btConfirmarPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        user = (Usuario) getIntent().getSerializableExtra("K_USER");

        tvEstadoPedido = findViewById(R.id.tvEstadoPedido);
        if(savedInstanceState != null) {
            mensajeEstadoPedido = savedInstanceState.getString(ESTADO_PEDIDO);
            tvEstadoPedido.setText(mensajeEstadoPedido);
        }
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
    protected void onStart() {
        super.onStart();
        initViews();
        setBtPedidoListener();

        tvUsername.setText(user.getNombre());
        tvUserAddress.setText(user.getDireccion());
        tvTamanoPizza.setText("Pizza " + user.getPizza().getTamanoPizza().toString());
        tvListaIngredientes.setText(user.getPizza().getStringListaIngredientes());
        tvPrice.setText((user.getPizza().getPrecio()) + "€");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mensajeEstadoPedido = tvEstadoPedido.getText().toString();
        outState.putString(ESTADO_PEDIDO, mensajeEstadoPedido);
    }

    private void initViews() {
        tvUsername = findViewById(R.id.txVwNombreUsuario);
        tvUserAddress = findViewById(R.id.txVwDireccionUsuario);
        tvTamanoPizza = findViewById(R.id.txVwTamanoPizza);
        tvListaIngredientes = findViewById(R.id.txVwIngredientesLista);
        btConfirmarPedido = findViewById(R.id.btConfirmarPedido);
        tvPrice = findViewById(R.id.tvPrice);
        setEstadoPedidoText();
    }

    private void setEstadoPedidoText() {
        mensajeEstadoPedido = (estadoPedido) ? "Pedido confirmado" : "Pedido no confirmado";
        tvEstadoPedido.setText(mensajeEstadoPedido);
    }

    private void setBtPedidoListener() {
        btConfirmarPedido.setOnClickListener(view -> {
            showAlertConfirmarPedido();
        });
    }

    private void showAlertConfirmarPedido() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(!estadoPedido) {
            builder.setTitle("Confirmar pedido");
            builder.setMessage("¿Quiere confirmar el pedido?");

            // Botón OK
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();  // Cerrar la alerta
                    estadoPedido = true;
                    setEstadoPedidoText();
                    showAlertPedidoConfirmado();
                }
            });

            builder.setNegativeButton("No", (dialogInterface, i) -> {
                dialogInterface.dismiss();
            });
        } else {
            builder.setTitle("Pedido ya confirmado");
            builder.setMessage("Revisa tu correo electrónico para consultar los detalles de su pedido");
        }

        // Mostrar la alerta
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertPedidoConfirmado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pedido confirmado correctamente");
        builder.setMessage("En breve recibirá un correo con los detalles de su pedido");

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
        builder.setMessage("¿Quiere revisar su pedido o volver al inicio?");

        // Botón OK
        builder.setPositiveButton("Revisar pedido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Código que se ejecuta al hacer clic en OK
                dialog.dismiss();  // Cerrar la alerta
            }
        });

        builder.setNegativeButton("Volver al inicio",  (dialogInterface, i) -> {
            volverAlInicio();
            dialogInterface.dismiss();
        });

        // Mostrar la alerta
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void volverAlInicio() {
        // Crea un intent para volver a la MainActivity
        Intent intent = new Intent(this, MainActivity.class);

        // Configura los flags para limpiar la pila de actividades
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Inicia la MainActivity con la configuración establecida
        startActivity(intent);

        // Finaliza la ThirdActivity para garantizar que se cierre correctamente
        finish();
    }

}