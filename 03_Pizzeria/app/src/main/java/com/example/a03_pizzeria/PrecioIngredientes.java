package com.example.a03_pizzeria;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class PrecioIngredientes implements View.OnClickListener {

    private MainActivity activity;

    public PrecioIngredientes(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        CheckBox checkBox = (CheckBox) v;
        boolean isChecked = checkBox.isChecked();
        if (isChecked) {
            MainActivity.precioIngredientes += 2;
        } else {
            MainActivity.precioIngredientes -= 2;
        }
        activity.calcularPrecioTotal(activity.findViewById(R.id.tvPrecio));
    }
}
