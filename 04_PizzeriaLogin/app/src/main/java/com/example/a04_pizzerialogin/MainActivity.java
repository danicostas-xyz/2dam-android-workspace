package com.example.a04_pizzerialogin;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a04_pizzerialogin.modelo.entidad.Usuario;
import com.example.a04_pizzerialogin.modelo.negocio.GestorUsuario;
import com.example.a04_pizzerialogin.modelo.persistencia.DaoUsuario;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String K_USER = "USUARIO";
    private final String MENSAJE_ALERTA = "MENSAJE_ALERTA";

    Usuario user;
    GestorUsuario gu;

    private String mensajeAlerta;

    EditText edText1;
    EditText edText2;
    Button btLogin;
    Button btSignUp;
    TextView tvAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DaoUsuario.meterUsuariosEnLista(); // Meto los usuarios en la lista antes de nada, como si fuera un fichero
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        /*
        * Si savedInstanceState != null significa que se ha guardado info en el Bundle outState
        * desde el método onSaveInstanceState.
        * La información almacenada solo se guarda una vez entre paso de activities, por lo que
        * si queremos persistir la información más de una vez, tenemos que almacenar esa info.
        * En este caso almacenamos la información en la varibale mensajeAlerta y luego utilizamos
        * dicha variable para ponerle el texto al TextView tvAlert.
        */

        if (savedInstanceState != null) {
            mensajeAlerta = savedInstanceState.getString(MENSAJE_ALERTA);
            tvAlert.setText(mensajeAlerta);
        }

        ActivityResultLauncher<Intent> ar = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == SignUpActivity.OK) {
                        Intent intent = result.getData();
                        user = (Usuario) intent.getSerializableExtra(K_USER);
                        edText1.setText(user.getNombre());
                        edText2.setText(user.getPass());
                    } else {
                        Toast.makeText(this, "Ha ocurrido un error, vuelva a intentarlo.", Toast.LENGTH_SHORT).show();
                    }
                });

        setBtLogInListener();
        setBtSignUpListener(ar);
    }

    private void setBtLogInListener() {
        btLogin.setOnClickListener(view -> {
            validarLogin();
        });
    }

    private void setBtSignUpListener(ActivityResultLauncher<Intent> ar) {
        btSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this,SignUpActivity.class);
            ar.launch(intent);
        });
    }

    private void validarLogin() {
        gu = new GestorUsuario();

        user = new Usuario();
        user.setNombre(edText1.getText().toString());
        user.setPass(edText2.getText().toString());

        int resultado = gu.validarUsuario(user);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        switch (resultado) {
            case 0:
                mensajeAlerta = "El usuario no existe";
                tvAlert.setText(mensajeAlerta);
                break;
            case 1:
                mensajeAlerta = "Usuario y/o contraseña incorrectos";
                tvAlert.setText(mensajeAlerta);
                break;
            case 2:
                intent.putExtra(K_USER, gu.getByName(user.getNombre()));
                startActivity(intent);
                break;
        }
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
        btLogin = findViewById(R.id.bt1);
        btSignUp = findViewById(R.id.btSignUp);
        tvAlert = findViewById(R.id.tvAlerta);
    }

    /*

    * Esta parte del código sirve para mostrar el mensaje de error en una ventana emergente.
    * Se ha comentado esta parte para que se muestre el mensaje en un TextView directamente, que
    * se mantiene en el outState para ser recuperado en caso de que se ejecute el método onCreate
    * de nuevo, por ejemplo, al rotar la pantalla del dispositivo.

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

    */
}