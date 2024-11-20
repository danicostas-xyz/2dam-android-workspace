package com.example.a05_calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        TextInputEditText etUsuario = findViewById(R.id.etUsuario);
        Button btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(view -> {

            boolean validacionUsuario = (etUsuario.getText() != null) ? validarUsuario(etUsuario.getText().toString()) : false;

            if (validacionUsuario) {

                Intent intent = new Intent(LoginActivity.this, CalculadoraActivity.class);
                intent.putExtra("K_USER", etUsuario.getText().toString());
                startActivity(intent);

            } else {
                Toast.makeText(this, "Introduce un usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validarUsuario(String u) {
        if (u.isBlank()) {
            return false;
        }
        return true;
    }
}