package com.example.a05_calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a05_calculadora.modelo.negocio.ClaseCalculadora;

import java.util.concurrent.atomic.AtomicReference;

public class CalculadoraActivity extends AppCompatActivity {

    ClaseCalculadora calculadora = ClaseCalculadora.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        String user = getIntent().getStringExtra("K_USER");
        final    TextView tvUsuario = findViewById(R.id.tvUsuario);
        tvUsuario.setText(user);

        final TextView tvCalculo = findViewById(R.id.tvResultado);
        final TextView tvInput = findViewById(R.id.tvInput);

        final Button btC = findViewById(R.id.btC);
        final Button btSumar = findViewById(R.id.btSumar);
        final Button btRestar = findViewById(R.id.btRestar);
        final Button btMultiplicar = findViewById(R.id.btMultiplicar);
        final Button btDividir = findViewById(R.id.btDividir);
        final Button btPunto = findViewById(R.id.btPunto);
        final Button btResultado = findViewById(R.id.btResultado);
        final Button btMasMenos = findViewById(R.id.btMasMenos);
        final Button bt0 = findViewById(R.id.bt0);
        final Button bt1 = findViewById(R.id.bt1);
        final Button bt2 = findViewById(R.id.bt2);
        final Button bt3 = findViewById(R.id.bt3);
        final Button bt4 = findViewById(R.id.bt4);
        final Button bt5 = findViewById(R.id.bt5);
        final Button bt6 = findViewById(R.id.bt6);
        final Button bt7 = findViewById(R.id.bt7);
        final Button bt8 = findViewById(R.id.bt8);
        final Button bt9 = findViewById(R.id.bt9);

        String input = "";
        String resultadoActualizable = "";
        double resultadoEstatico = 0;

    }

    private double construirNumero(Button b) {

        AtomicReference<Double> numero = new AtomicReference<>((double) 0);

        b.setOnClickListener(view -> {

            if (Integer.parseInt(b.toString()) == 0) {
                numero.set((double) 0);
            }


        });


        return numero.get();
    }


    private void actualizarInput(TextView tvInput) {





    }

    private double calculo(){

        double calculo = 0;
        return calculo;
    }


}