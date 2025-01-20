package xyz.danicostas.a06_recyclerview;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import xyz.danicostas.a06_recyclerview.entidad.Videojuego;
import xyz.danicostas.a06_recyclerview.singleton.ListaVideojuegosSingleton;

public class EditGameActivity extends AppCompatActivity {

    private LinearLayout ly;
    private TextView idJuego;
    private TextView nombreJuego;
    private TextView companiaJuego;
    private EditText edNuevoNombre;
    private EditText edNuevaCompania;
    private Button btGuardar;
    private Button btCancelar;
    private Button btEditarColor;
    String colorForResult;

    private List<Videojuego> listaVj = ListaVideojuegosSingleton.getInstance().getListaVideojuegos();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);
        setViews();
        intent = getIntent();
        Videojuego vj = (Videojuego) intent.getSerializableExtra("VideoJuego");
        nombreJuego.setText(vj.getNombre());
        companiaJuego.setText(vj.getCompania());
        idJuego.setText(String.valueOf(vj.getId()));
        String color = vj.getColor();
        int color2 = color != null ? Color.parseColor(color) : Color.parseColor("#FFFFFF");
        if (color != null) {ly.setBackgroundColor(color2);};
        if(colorForResult != null) {
            ly.setBackgroundColor(Integer.parseInt(colorForResult));
        }


        btGuardar.setOnClickListener(view -> {
            Videojuego vjboton = new Videojuego();
            vjboton.setId(Integer.parseInt(idJuego.getText().toString()));
            vjboton.setNombre(edNuevoNombre.getText().toString());
            vjboton.setCompania(edNuevaCompania.getText().toString());
            vjboton.setColor(String.valueOf(colorForResult));
            guardar(vjboton, intent.getIntExtra("Activity", 90));
            Log.i("TAG", "onCreate: " + vjboton.getId() + vjboton.getNombre() + vjboton.getCompania());
        });

        btCancelar.setOnClickListener(view -> {
            cancelar();
        });

        final ActivityResultLauncher<Intent> activityForResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == ColorSelectionActivity.OK) {
                                Intent intent = result.getData();
                                colorForResult = intent.getStringExtra("Color");

                            } else {
                                Toast.makeText(this, "Edición de color cancelada", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

        btEditarColor.setOnClickListener(view -> {
            Intent intent = new Intent(this,ColorSelectionActivity.class);
            activityForResultLauncher.launch(intent);
        });
    }

    private void setViews() {
        ly = findViewById(R.id.linearLayout);
        idJuego = findViewById(R.id.idJuego);
        nombreJuego = findViewById(R.id.nombreVj);
        companiaJuego = findViewById(R.id.companiaVj);
        edNuevoNombre = findViewById(R.id.edNuevoNombre);
        edNuevaCompania = findViewById(R.id.edNuevaCompania);
        btGuardar = findViewById(R.id.btnGuardar);
        btCancelar = findViewById(R.id.btnCancelar);
        btEditarColor = findViewById(R.id.btEditarColor);
    }


    private void cancelar () {
        finish();
    }

    private void guardar (Videojuego vj, int activity){

        if (activity == 0) {
            listaVj.add(vj);
            ListaVideojuegosSingleton.getInstance().actualizarLista(listaVj);
        }

        if (activity == 1) {
            listaVj.set((vj.getId() - 1),vj);
            ListaVideojuegosSingleton.getInstance().actualizarLista(listaVj);
        }

        finish();
    }
}



