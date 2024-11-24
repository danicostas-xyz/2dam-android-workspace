package com.example.a04_pizzerialogin;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a04_pizzerialogin.modelo.entidad.Usuario;
import com.example.a04_pizzerialogin.modelo.negocio.GestorUsuario;
import com.example.a04_pizzerialogin.modelo.persistencia.DaoUsuario;

import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    public final static String K_USER = "USUARIO";
    private final String MENSAJE_ALERTA = "MENSAJE_ALERTA";
    public final static int OK = 1;
    public final static int KO = 0;

    Usuario user;
    private String mensajeAlerta;
    GestorUsuario gu;

    EditText edText1;
    EditText edText2;
    EditText edText3;
    Button btSignUp;
    TextView tvAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();

        if (savedInstanceState != null) {
            mensajeAlerta = savedInstanceState.getString(MENSAJE_ALERTA);
            tvAlert.setText(mensajeAlerta);
        }

        setBtSignUpListener();
    }

    private void setBtSignUpListener() {
        btSignUp.setOnClickListener(view -> {

            Intent data = new Intent();

            String nombre = edText1.getText().toString();
            String pass = edText2.getText().toString();
            String direccion = edText3.getText().toString();

            if (nombre.isBlank() || pass.isBlank()) {
                mensajeAlerta = "Introduce un usuario y una contraseña";
                tvAlert.setText(mensajeAlerta);
            } else {
                gu = new GestorUsuario();
                user = new Usuario();
                user.setNombre(nombre);
                user.setPass(pass);
                user.setDireccion(direccion);
                gu.addUser(user);
                data.putExtra(K_USER, user);
                setResult(OK, data);
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("MainActivity", "onSaveInstanceState()");
        outState.putString("MENSAJE_ALERTA", mensajeAlerta);
    }

    private void initViews() {
        edText1 = findViewById(R.id.edText1);
        edText2 = findViewById(R.id.edText2);
        edText3 = findViewById(R.id.edText3);
        btSignUp = findViewById(R.id.btSignUp);
        tvAlert = findViewById(R.id.tvAlerta);
    }

}