package com.example.a04_pizzerialogin.modelo.persistencia;

import com.example.a04_pizzerialogin.modelo.entidad.Usuario;
import java.util.ArrayList;

public class DaoUsuario {

    static final ArrayList<Usuario> lista = new ArrayList<>();

    static final Usuario u1 = new Usuario("Avenida de Castelao", "1234", "Daniel");
    static final Usuario u2 = new Usuario("Joaquín Martínez", "4321", "Nahuel");
    static final Usuario u3 = new Usuario("Calle Ferrocarril", "0000", "Pedro");

    public static void meterUsuariosEnLista() {
        lista.add(u1);
        lista.add(u2);
        lista.add(u3);
    }

    public Usuario getByName(String nombre) {

        Usuario u = null;

        for (Usuario user : lista) {

            if (user.getNombre().equals(nombre)) {
                u = user;
            }

        }

        return u;
    }

}
