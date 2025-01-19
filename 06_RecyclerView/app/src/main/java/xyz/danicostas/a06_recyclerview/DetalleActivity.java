package xyz.danicostas.a06_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;



import java.util.List;

import xyz.danicostas.a06_recyclerview.adaptador.AdaptadorVideojuegoPersonalizado;
import xyz.danicostas.a06_recyclerview.entidad.Videojuego;
import xyz.danicostas.a06_recyclerview.singleton.ListaVideojuegosSingleton;

public class DetalleActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private AdaptadorVideojuegoPersonalizado adaptadorUsuario;
    private List<Videojuego> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        recyclerViewUser = findViewById(R.id.rViewUsuario);
        recyclerViewUser.setHasFixedSize(true);

        // use a linear layout manager, esta vez horizontal
        recyclerViewUser.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false));

        listaUsuarios = ListaVideojuegosSingleton.getInstance().getListaVideojuegos();
        adaptadorUsuario = new AdaptadorVideojuegoPersonalizado(listaUsuarios, DetalleActivity.this);
        recyclerViewUser.setAdapter(adaptadorUsuario);
        ListaVideojuegosSingleton.getInstance().getListaVideojuegosLiveData().observe(this, videojuegos -> {
            adaptadorUsuario.notifyDataSetChanged();
        });

        Button btVolver = findViewById(R.id.btVolver);
        btVolver.setOnClickListener(view -> {
            finish();
        });
    }
}