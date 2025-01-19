package xyz.danicostas.a06_recyclerview.adaptador;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import xyz.danicostas.a06_recyclerview.EditGameActivity;
import xyz.danicostas.a06_recyclerview.R;
import xyz.danicostas.a06_recyclerview.entidad.Videojuego;
import xyz.danicostas.a06_recyclerview.singleton.ListaVideojuegosSingleton;

public class AdaptadorVideojuegoPersonalizado extends RecyclerView.Adapter<AdaptadorVideojuegoPersonalizado.ViewHolder> {

   private List<Videojuego> listaVideojuegos;
   private Context context;

   public AdaptadorVideojuegoPersonalizado(List<Videojuego> listaVideojuegos, Context context) {
       this.listaVideojuegos = listaVideojuegos;
       this.context = context;
   }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       private TextView id;
       private TextView nombre;
       private TextView compania;
       private Button botonEditar;
       private Button botonEliminar;

        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.idJuego);
            nombre = v.findViewById(R.id.nombreVj);
            compania = v.findViewById(R.id.companiaVj);

            botonEditar = v.findViewById(R.id.btEditar);
            botonEliminar = v.findViewById(R.id.btEliminar);
        }
    }

   //será quien devuelva el ViewHolder con el layout seteado que previamente definimos
   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_view, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
   }

   //será quien se encargue de establecer los objetos en el ViewHolder
   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {

       String sId = String.valueOf(listaVideojuegos.get(position).getId());
       holder.id.setText(sId);
       holder.nombre.setText(listaVideojuegos.get(position).getNombre());
       String sCompania = String.valueOf(listaVideojuegos.get(position).getCompania());
       holder.compania.setText(sCompania);

       holder.botonEditar.setOnClickListener(view -> {
           Intent intent = new Intent(context, EditGameActivity.class);
           intent.putExtra("NombreJuego", holder.nombre.getText());
           intent.putExtra("CompaniaJuego", holder.compania.getText());
           intent.putExtra("IDJuego", holder.id.getText());
           Bundle b = new Bundle();
           startActivity(context, intent, b);
           notifyDataSetChanged();
       });

       holder.botonEliminar.setOnClickListener(view -> {
           Toast.makeText(holder.id.getContext(), "Eliminando usuario " + sId, Toast.LENGTH_SHORT).show();
           ListaVideojuegosSingleton.getInstance().borrar(listaVideojuegos.get(position));
           notifyDataSetChanged();//Siempre notificamos cuando cambiamos los datos de una lista
       });
   }

   //será quien devuelva la cantidad de items que se encuentra en la lista
   @Override
   public int getItemCount() {
       return listaVideojuegos.size();
   }

}