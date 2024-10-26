package com.example.a04_pizzerialogin;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a04_pizzerialogin.modelo.entidad.Usuario;
import com.example.a04_pizzerialogin.modelo.negocio.GestorUsuario;
import com.example.a04_pizzerialogin.modelo.persistencia.DaoUsuario;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

//    public final static String K_USERNAME = "nombre";
//    public final static String K_PASSWORD = "pass";
    public final static String K_USER = "usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DaoUsuario.meterUsuariosEnLista(); // Meto los usuarios en la lista antes de nada, como si fuera un fichero
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edText1 = findViewById(R.id.edText1);
        EditText edText2 = findViewById(R.id.edText2);
        Button bt1 = findViewById(R.id.bt1);


        /* bt1.setOnClickListener(v -> {
            String nombre = edText1.getText().toString();
            String pass = edText2.getText().toString();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(K_USERNAME, nombre);
            intent.putExtra(K_PASSWORD, pass);
            startActivity(intent);
        });*/

        bt1.setOnClickListener(view -> {

            GestorUsuario gu = new GestorUsuario();

            Usuario us = new Usuario();
            us.setNombre(edText1.getText().toString());
            us.setPass(edText2.getText().toString());

            int resultado = gu.validarUsuario(us);

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            switch (resultado) {
                case 0:
                    showAlert2();
                    break;
                case 1:
                    intent.putExtra(K_USER, gu.getByName(us.getNombre()));
                    startActivity(intent);
                    break;
            }
        });
    }

    private void showAlert2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Usuario y/o contraseña incorrectos");

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


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("El usuario no existe");

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