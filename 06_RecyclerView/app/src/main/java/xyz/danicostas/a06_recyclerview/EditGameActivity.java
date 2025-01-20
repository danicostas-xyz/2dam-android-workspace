package xyz.danicostas.a06_recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        nombreJuego.setText(intent.getStringExtra("NombreJuego"));
        companiaJuego.setText(intent.getStringExtra("CompaniaJuego"));
        idJuego.setText(intent.getStringExtra("IDJuego"));
        btGuardar.setOnClickListener(view -> {
            Videojuego vj = new Videojuego();
            vj.setId(Integer.parseInt(idJuego.getText().toString()));
            vj.setNombre(edNuevoNombre.getText().toString());
            vj.setCompania(edNuevaCompania.getText().toString());
            guardar(vj);
            Log.i("TAG", "onCreate: " + vj.getId() + vj.getNombre() + vj.getCompania());
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

    private void guardar (Videojuego vj){
        listaVj.set((vj.getId() - 1),vj);
        ListaVideojuegosSingleton.getInstance().actualizarLista(listaVj);
        finish();
    }



}



