package xyz.danicostas.a06_recyclerview;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.List;

import xyz.danicostas.a06_recyclerview.adaptador.AdaptadorVideojuego;
import xyz.danicostas.a06_recyclerview.entidad.Videojuego;
import xyz.danicostas.a06_recyclerview.singleton.ListaVideojuegosSingleton;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewGame;
    private AdaptadorVideojuego adaptadorVideojuego;
    private Button botonSegunda;
    private List<Videojuego> listaVideojuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewGame = findViewById(R.id.rViewUsuario);
        botonSegunda = findViewById((R.id.segunda));

        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        recyclerViewGame.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        recyclerViewGame.setLayoutManager(new LinearLayoutManager(this));

        listaVideojuegos = ListaVideojuegosSingleton.getInstance().getListaVideojuegos();

        // Asociamos un adapter (ver más adelante cómo definirlo)
        adaptadorVideojuego = new AdaptadorVideojuego(listaVideojuegos);
        recyclerViewGame.setAdapter(adaptadorVideojuego);

        botonSegunda.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
            startActivity(intent);
        });

     }

    @Override
    protected void onResume() {
        super.onResume();
        //Este método notifica que el conjunto de datos de la lista ha cambiado y que hay
        //que refrescarla
        recyclerViewGame.getAdapter().notifyDataSetChanged();
    }
}