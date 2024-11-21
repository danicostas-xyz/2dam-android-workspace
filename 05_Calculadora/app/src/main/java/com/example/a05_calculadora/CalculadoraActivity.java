package com.example.a05_calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a05_calculadora.modelo.negocio.ClaseCalculadora;

public class CalculadoraActivity extends AppCompatActivity {

    ClaseCalculadora calculadora = ClaseCalculadora.getInstance();
    StringBuilder inputUsuario = new StringBuilder();
    StringBuilder historico = new StringBuilder();
    double acumulacionCalculo;
    double inputACalcular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        String user = getIntent().getStringExtra("K_USER");
        final TextView tvUsuario = findViewById(R.id.tvUsuario);
        tvUsuario.setText(user);

        final TextView tvInputUsuario = findViewById(R.id.tvResultado);
        final TextView tvHistorico = findViewById(R.id.tvHistorico);

        final Button btC = findViewById(R.id.btC);
        final Button btBorrar = findViewById(R.id.btBorrar);
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


        View.OnClickListener commonListenerNumeros = view -> {

            String s = historico.toString(); // Si no hago aquí el toString(), en la línea siguiente, si hago el equals() al StringBuilder original, no me entra por el if aunque sí que sea .equals("0")
            if (s.equals("0")) {
                historico.delete(0, 1);
            }

            if (view.getId() == R.id.bt0) {
                historico.append("0");
                inputUsuario.append("0");
            } else if (view.getId() == R.id.bt1) {
                historico.append("1");
                inputUsuario.append("1");
            } else if (view.getId() == R.id.bt2) {
                historico.append("2");
                inputUsuario.append("2");
            } else if (view.getId() == R.id.bt3) {
                historico.append("3");
                inputUsuario.append("3");
            } else if (view.getId() == R.id.bt4) {
                historico.append("4");
                inputUsuario.append("4");
            } else if (view.getId() == R.id.bt5) {
                historico.append("5");
                inputUsuario.append("5");
            } else if (view.getId() == R.id.bt6) {
                historico.append("6");
                inputUsuario.append("6");
            } else if (view.getId() == R.id.bt7) {
                historico.append("7");
                inputUsuario.append("7");
            } else if (view.getId() == R.id.bt8) {
                historico.append("8");
                inputUsuario.append("8");
            } else if (view.getId() == R.id.bt9) {
                historico.append("9");
                inputUsuario.append("9");
            }

            s = historico.toString();
            if (s.length() >= 3) {
                char operador = s.charAt(s.length() - 2);
                if (operador == '+' || operador == '-' || operador == 'X' || operador == '/') {
                    inputACalcular = Double.parseDouble(inputUsuario.toString());
                    double resultado = calcular(operador, acumulacionCalculo, inputACalcular);
                    acumulacionCalculo = resultado;
                    inputUsuario.replace(0, inputUsuario.length()-1, String.valueOf(acumulacionCalculo));
                    tvInputUsuario.setText(inputUsuario);
                }
            }

            tvHistorico.setText(historico);
            tvInputUsuario.setText(inputUsuario);

        };

        View.OnClickListener commonListenerOperadores = view -> {

            if(historico.length() == 0) {
                return;
            }

            String operador = (String.valueOf(historico.charAt(historico.length() - 1)));

            if (operador.equals("X") || operador.equals("-") || operador.equals("+") || operador.equals("/")) {

                if (view.getId() == R.id.btSumar) {
                    historico.setCharAt(historico.length() - 1, '+');
                } else if (view.getId() == R.id.btRestar) {
                    historico.setCharAt(historico.length() - 1, '-');
                } else if (view.getId() == R.id.btMultiplicar) {
                    historico.setCharAt(historico.length() - 1, 'X');
                } else if (view.getId() == R.id.btDividir) {
                    historico.setCharAt(historico.length() - 1, '/');
                }

            } else {

                if (view.getId() == R.id.btSumar) {
                    historico.append("+");
                } else if (view.getId() == R.id.btRestar) {
                    historico.append("-");
                } else if (view.getId() == R.id.btMultiplicar) {
                    historico.append("X");
                } else if (view.getId() == R.id.btDividir) {
                    historico.append("/");
                }
            }

            tvHistorico.setText(historico.toString());
        };

        final Button[] arrayBotonesNumero = {
                bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0
        };

        for (Button b :
                arrayBotonesNumero) {
            b.setOnClickListener(commonListenerNumeros);
        }

        final Button[] arrayBotonesOperadores = {
                btSumar, btRestar, btMultiplicar, btDividir
        };

        for (Button b :
                arrayBotonesOperadores) {
            b.setOnClickListener(commonListenerOperadores);
        }

        btC.setOnClickListener(view -> {
            historico.delete(0, historico.length());
            historico.append("0");
            tvHistorico.setText(historico);
            acumulacionCalculo = 0;
            inputUsuario.delete(0, inputUsuario.length());
            tvInputUsuario.setText("0");
        });

        btBorrar.setOnClickListener(view -> {
            if(historico.length() > 0) {
                historico.deleteCharAt(historico.length()-1);
                tvHistorico.setText(historico);
            }

            if(historico.length() == 0){
                tvHistorico.setText("0");
            }

            if(inputUsuario.length() > 0){
                inputUsuario.deleteCharAt(inputUsuario.length()-1);
                tvInputUsuario.setText(inputUsuario);
            }

            if(inputUsuario.length() == 0){
                tvInputUsuario.setText("0");
            }



        });

        btResultado.setOnClickListener(view -> {

            char operador = (historico.charAt(historico.length()-1));
            double resultado = calcular(operador, acumulacionCalculo, inputACalcular);

            acumulacionCalculo = resultado;
        });


    }

    private double calcular(char operador, double n1, double n2) {


        switch (operador) {
            case '+':
                return calculadora.sumar(n1,n2);
            case '-':
                return calculadora.restar(n1,n2);
            case 'X':
                return calculadora.multiplicar(n1,n2);
            case '/':
                return calculadora.dividir(n1,n2);
        }


        return n1;
    }
}