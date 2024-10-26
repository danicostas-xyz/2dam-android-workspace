package com.example.a00_appjava2;

import android.util.Log;
import android.view.View;

public class EventoOnClick implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Log.i("Tag1", "onClick: Por pantalla desde nueva clase que implementa");
    }
}
