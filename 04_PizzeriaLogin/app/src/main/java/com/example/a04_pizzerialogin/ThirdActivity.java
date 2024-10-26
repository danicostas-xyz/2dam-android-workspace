package com.example.a04_pizzerialogin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a04_pizzerialogin.modelo.entidad.Usuario;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Usuario user = (Usuario) getIntent().getSerializableExtra("K_USER");

        TextView txVwNombreUsuario = findViewById(R.id.txVwNombreUsuario);
        TextView txVwDireccionUsuario = findViewById(R.id.txVwDireccionUsuario);
        TextView txVwTamanoPizza = findViewById(R.id.txVwTamanoPizza);
        TextView txVwIngredientesLista = findViewById(R.id.txVwIngredientesLista);

        txVwNombreUsuario.setText(user.getNombre());
        txVwDireccionUsuario.setText(user.getDireccion());
        txVwTamanoPizza.setText("Pizza " + user.getPizza().getTamanoPizza().toString());
        txVwIngredientesLista.setText(user.getPizza().getStringListaIngredientes());

        Button btConfirmarPedido = findViewById(R.id.btConfirmarPedido);
        btConfirmarPedido.setOnClickListener(view -> {
            showAlertPedidoConfirmado();
        });


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
}