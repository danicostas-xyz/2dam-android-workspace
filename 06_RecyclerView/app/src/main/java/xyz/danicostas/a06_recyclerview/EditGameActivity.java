package xyz.danicostas.a06_recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import xyz.danicostas.a06_recyclerview.entidad.Videojuego;
import xyz.danicostas.a06_recyclerview.singleton.ListaVideojuegosSingleton;

public class EditGameActivity extends AppCompatActivity {

    private TextView idJuego;
    private TextView nombreJuego;
    private TextView companiaJuego;
    private EditText edNuevoNombre;
    private EditText edNuevaCompania;
    private Button btGuardar;
    private Button btCancelar;
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

        btGuardar.setOnClickListener(view -> {
            Videojuego vjboton = new Videojuego();
            vjboton.setId(Integer.parseInt(idJuego.getText().toString()));
            vjboton.setNombre(edNuevoNombre.getText().toString());
            vjboton.setCompania(edNuevaCompania.getText().toString());
            guardar(vjboton, intent.getIntExtra("Activity", 90));
            Log.i("TAG", "onCreate: " + vjboton.getId() + vjboton.getNombre() + vjboton.getCompania());
        });

        btCancelar.setOnClickListener(view -> {
            cancelar();
        });

    }

    private void setViews() {
        idJuego = findViewById(R.id.idJuego);
        nombreJuego = findViewById(R.id.nombreVj);
        companiaJuego = findViewById(R.id.companiaVj);
        edNuevoNombre = findViewById(R.id.edNuevoNombre);
        edNuevaCompania = findViewById(R.id.edNuevaCompania);
        btGuardar = findViewById(R.id.btnGuardar);
        btCancelar = findViewById(R.id.btnCancelar);
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



