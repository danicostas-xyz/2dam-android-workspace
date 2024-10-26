package com.example.a04_pizzerialogin.modelo.negocio;


import com.example.a04_pizzerialogin.modelo.entidad.Pizza;
import com.example.a04_pizzerialogin.modelo.entidad.Usuario;
import com.example.a04_pizzerialogin.modelo.persistencia.DaoUsuario;

public class GestorUsuario {

    /**
     * Método que valida un usuario pasado por parámetro para comprobar, primero, si existe en la persistencia.
     * En caso de que exista, se verifica que su contraseña coincida con la almacenada en persistencia.
     *
     * @param us el objeto usuario pasado por parámetro
     * @return 0 en caso de que el usuario introducido no exista, 1 en caso de que el usuario exista
     * pero su contraseña sea incorrecta y 2 en caso de que el usuario y la contraseña sean correctas.
     */
    public int validarUsuario(Usuario us) {

        DaoUsuario dao = new DaoUsuario();

//        if (us.equals(dao.getByName(us.getNombre()))){
//            // El método equals está sobreescrito para que de true si nombre y pass coinciden
//            return 1;
//        } else return 0;

        if (dao.getByName(us.getNombre()) != null) {
            if (us.getNombre().equals(dao.getByName(us.getNombre()).getNombre())) {
                if (us.getPass().equals(dao.getByName(us.getNombre()).getPass())) {
                    return 2; // Existe y contraseña correcta
                }
                return 1; // Existe pero contraseña incorrecta
            }
        }
        return 0; // == Null -> No existe
    }

    public Usuario getByName(String nombre) {
        DaoUsuario dao = new DaoUsuario();
        return dao.getByName(nombre);
    }

    public void addPizzaUsuario(Usuario us, Pizza pz) {
        us.setPizza(pz);
    }
}
