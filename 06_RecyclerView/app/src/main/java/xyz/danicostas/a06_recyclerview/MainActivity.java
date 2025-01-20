package xyz.danicostas.a06_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;



import java.util.List;

import xyz.danicostas.a06_recyclerview.adaptador.AdaptadorVjPersonalizado;
import xyz.danicostas.a06_recyclerview.entidad.Videojuego;
import xyz.danicostas.a06_recyclerview.singleton.ListaVideojuegosSingleton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private AdaptadorVjPersonalizado adaptadorUsuario;
    private List<Videojuego> listaVideojuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewUser = findViewById(R.id.rViewUsuario);
        recyclerViewUser.setHasFixedSize(true);

        // use a linear layout manager, esta vez horizontal
        recyclerViewUser.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false));

        listaVideojuegos = ListaVideojuegosSingleton.getInstance().getListaVideojuegos();
        adaptadorUsuario = new AdaptadorVjPersonalizado(listaVideojuegos, MainActivity.this);
        recyclerViewUser.setAdapter(adaptadorUsuario);
        ListaVideojuegosSingleton.getInstance().getListaVideojuegosLiveData().observe(this, videojuegos -> {
            adaptadorUsuario.notifyDataSetChanged();
        });

        Button btAddVj = findViewById(R.id.btAddVj);
        btAddVj.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, EditGameActivity.class);
            Videojuego vj = new Videojuego();
            vj.setId(listaVideojuegos.size() + 1);
            vj.setNombre("Nombre Videojuego");
            vj.setCompania("Nombre Compañía");
            i.putExtra("VideoJuego", vj);
            i.putExtra("Activity", 0);
            startActivity(i);
        });
    }
}