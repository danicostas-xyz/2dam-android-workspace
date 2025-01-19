package xyz.danicostas.a06_recyclerview.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import xyz.danicostas.a06_recyclerview.entidad.Videojuego;

public class AdaptadorVideojuego extends RecyclerView.Adapter<AdaptadorVideojuego.ViewHolder> {

   private List<Videojuego> listaVideojuegos;

   public AdaptadorVideojuego(List<Videojuego> listaVideojuegos) {
       this.listaVideojuegos = listaVideojuegos;
   }

    // Obtener referencias de los componentes visuales para cada elemento
    // Es decir, referencias de los EditText, TextViews, Buttons que haya en el view
    // que usaremos para inflar en cada fila del recicler
   public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView edad;

        public ViewHolder(View v) {
            super(v);
            nombre = v.findViewById(android.R.id.text1);
            edad = v.findViewById(android.R.id.text2);
        }
    }

    // El layout manager invocará este método
    // para renderizar cada elemento del RecyclerView
   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // Aquí podemos definir tamaños, márgenes, paddings, etc
       View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.two_line_list_item, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
   }

    // Este método se usa asigna valores para cada elemento de la lista o acciones de los
    //elementos
   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
       // - obtenemos un elemento del dataset según su posición
       // - reemplazamos el contenido usando tales datos
       holder.nombre.setText(listaVideojuegos.get(position).getNombre());
   }

   // Método que define la cantidad de elementos del RecyclerView
   @Override
   public int getItemCount() {
       return listaVideojuegos.size();
   }

}