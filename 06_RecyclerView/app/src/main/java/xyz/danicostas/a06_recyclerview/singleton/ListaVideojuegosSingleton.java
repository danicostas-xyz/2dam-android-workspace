package xyz.danicostas.a06_recyclerview.singleton;

import android.util.Log;


import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import xyz.danicostas.a06_recyclerview.entidad.Videojuego;

public class ListaVideojuegosSingleton {

    private static ListaVideojuegosSingleton instance;
    private List<Videojuego> listaVideojuegos;
    private int contador = 1;
    private MutableLiveData<List<Videojuego>> listaVideojuegosLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Videojuego>> getListaVideojuegosLiveData() {
        return listaVideojuegosLiveData;
    }

    public void actualizarLista(List<Videojuego> listaActualizada) {
        listaVideojuegosLiveData.setValue(listaActualizada);
    }

    private ListaVideojuegosSingleton(){
        super();
    }

    public static ListaVideojuegosSingleton getInstance() {
        if(instance == null){
            instance = new ListaVideojuegosSingleton();
            instance.inicializar();
        }
        return instance;
    }

    public void inicializar(){
        listaVideojuegos = new ArrayList<>();
        Videojuego videojuego = new Videojuego();
        videojuego.setId(contador++);
        videojuego.setNombre("Fifa 2005");
        videojuego.setCompania("EA Sports");
        videojuego.setColor("#7DE2D1");

        listaVideojuegos.add(videojuego);

        videojuego = new Videojuego();
        videojuego.setId(contador++);
        videojuego.setNombre("Doom Eternal");
        videojuego.setCompania("Bethesda");
        videojuego.setColor("#EC9192");

        listaVideojuegos.add(videojuego);

        videojuego = new Videojuego();
        videojuego.setId(contador++);
        videojuego.setNombre("Fortnite");
        videojuego.setCompania("Epic Games");
        videojuego.setColor("#9FB7B9");

        listaVideojuegos.add(videojuego);

        videojuego = new Videojuego();
        videojuego.setId(contador++);
        videojuego.setNombre("Red Dead Redemption 2");
        videojuego.setCompania("Rockstar");
        videojuego.setColor("#F7FF68");

        listaVideojuegos.add(videojuego);

        videojuego = new Videojuego();
        videojuego.setId(contador++);
        videojuego.setNombre("Death Stranding");
        videojuego.setCompania("Kojima Productions");
        videojuego.setColor("#BCEBCB");

        listaVideojuegos.add(videojuego);

        Log.i("ListaUsuarioSingleton", "########" + listaVideojuegos);
    }

    public List<Videojuego> getListaVideojuegos() {
        return listaVideojuegos;
    }

    public void borrar(Videojuego videojuego){
        listaVideojuegos.remove(videojuego);
    }
}
