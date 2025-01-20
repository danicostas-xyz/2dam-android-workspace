package xyz.danicostas.a06_recyclerview.adaptador;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import xyz.danicostas.a06_recyclerview.EditGameActivity;
import xyz.danicostas.a06_recyclerview.R;
import xyz.danicostas.a06_recyclerview.entidad.Videojuego;
import xyz.danicostas.a06_recyclerview.singleton.ListaVideojuegosSingleton;

public class AdaptadorVjPersonalizado extends RecyclerView.Adapter<AdaptadorVjPersonalizado.ViewHolder> {

   private List<Videojuego> listaVideojuegos;
   private Context context;

   public AdaptadorVjPersonalizado(List<Videojuego> listaVideojuegos, Context context) {
       this.listaVideojuegos = listaVideojuegos;
       this.context = context;
   }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       private LinearLayout ly;
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
            ly = v.findViewById(R.id.linearLayout);

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
       String color = listaVideojuegos.get(position).getColor();
       int color2 = color != null ? Color.parseColor(color) : Color.parseColor("#FFFFFF");
       holder.ly.setBackgroundColor(color2);

       holder.botonEditar.setOnClickListener(view -> {
           Intent intent = new Intent(context, EditGameActivity.class);
           Videojuego vj = new Videojuego();
           vj.setId(Integer.parseInt(holder.id.getText().toString()));
           vj.setNombre(holder.nombre.getText().toString());
           vj.setCompania(holder.compania.getText().toString());
           Drawable background = holder.ly.getBackground();
           int backgroundColor = ((ColorDrawable) background).getColor();
           vj.setColor(String.valueOf("#E33333"));
           intent.putExtra("VideoJuego", vj);
           intent.putExtra("Activity", 1);
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