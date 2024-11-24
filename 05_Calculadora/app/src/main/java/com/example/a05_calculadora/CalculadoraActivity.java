package com.example.a05_calculadora;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a05_calculadora.modelo.negocio.ClaseCalculadora;

public class CalculadoraActivity extends AppCompatActivity {

    ClaseCalculadora calculadora = ClaseCalculadora.getInstance();
    StringBuilder inputUsuario = new StringBuilder();
    StringBuilder resultado = new StringBuilder();
    double acumulacionCalculo;
    double inputACalcular;
    boolean isNewNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        String user = getIntent().getStringExtra("K_USER");
        final TextView tvUsuario = findViewById(R.id.tvUsuario);
        tvUsuario.setText(user);

        final TextView tvInputUsuario = findViewById(R.id.tvInputUsuario);
        final TextView tvResultado = findViewById(R.id.tvResultado);

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

            String s = inputUsuario.toString(); // Si no hago aquí el toString(), en la línea siguiente,
            // si hago el equals() al StringBuilder original, no me entra por el if aunque sí que sea .equals("0")

            if (s.equals("0")) {
                inputUsuario.delete(0, 1); // Para que me borre el 0 si hay 0 al principio
            }

            if (view.getId() == R.id.bt0) {
                inputUsuario.append("0");
            } else if (view.getId() == R.id.bt1) {
                inputUsuario.append("1");
            } else if (view.getId() == R.id.bt2) {
                inputUsuario.append("2");
            } else if (view.getId() == R.id.bt3) {
                inputUsuario.append("3");
            } else if (view.getId() == R.id.bt4) {
                inputUsuario.append("4");
            } else if (view.getId() == R.id.bt5) {
                inputUsuario.append("5");
            } else if (view.getId() == R.id.bt6) {
                inputUsuario.append("6");
            } else if (view.getId() == R.id.bt7) {
                inputUsuario.append("7");
            } else if (view.getId() == R.id.bt8) {
                inputUsuario.append("8");
            } else if (view.getId() == R.id.bt9) {
                inputUsuario.append("9");
            }

            s = inputUsuario.toString();

            if (s.length() >= 3) {

                char operador = s.charAt(s.length() - 2);
                if (operador == '+' || operador == '-' || operador == 'X' || operador == '/') {

                }
            }

            tvInputUsuario.setText(inputUsuario);

        };

        View.OnClickListener commonListenerOperadores = view -> {

            if(inputUsuario.length() == 0) {
                return;
            }

            String operador = (String.valueOf(inputUsuario.charAt(inputUsuario.length() - 1)));

            if (operador.equals("X") || operador.equals("-") || operador.equals("+") || operador.equals("/")) {

                if (view.getId() == R.id.btSumar) {
                    inputUsuario.setCharAt(inputUsuario.length() - 1, '+');
                } else if (view.getId() == R.id.btRestar) {
                    inputUsuario.setCharAt(inputUsuario.length() - 1, '-');
                } else if (view.getId() == R.id.btMultiplicar) {
                    inputUsuario.setCharAt(inputUsuario.length() - 1, 'X');
                } else if (view.getId() == R.id.btDividir) {
                    inputUsuario.setCharAt(inputUsuario.length() - 1, '/');
                }

            } else {

                if (view.getId() == R.id.btSumar) {
                    inputUsuario.append("+");
                } else if (view.getId() == R.id.btRestar) {
                    inputUsuario.append("-");
                } else if (view.getId() == R.id.btMultiplicar) {
                    inputUsuario.append("X");
                } else if (view.getId() == R.id.btDividir) {
                    inputUsuario.append("/");
                }
            }

            if (acumulacionCalculo == 0) {
                acumulacionCalculo = Double.parseDouble(inputUsuario.deleteCharAt(inputUsuario.length()-1).toString());
            }
            inputACalcular = Double.parseDouble(inputUsuario.deleteCharAt(inputUsuario.length()-1).toString());
            isNewNumber = true;

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
            inputUsuario.setLength(0);
            inputUsuario.append("0");
            acumulacionCalculo = 0;
            tvInputUsuario.setText(inputUsuario);
        });

        btBorrar.setOnClickListener(view -> {
            if(inputUsuario.length() > 0) {
                inputUsuario.deleteCharAt(inputUsuario.length()-1);
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
            String s = inputUsuario.toString();
            char operador = s.charAt(s.length() - 2);
        });

        btMasMenos.setOnClickListener(view -> {
            double numero = Double.parseDouble(inputUsuario.toString());
            if (numero > 0) {
                inputUsuario.insert(0, '-');
            } else {
                inputUsuario.deleteCharAt(0);
            }
            tvInputUsuario.setText(inputUsuario);
        });
    }



    private double calcular(char operador, double n1, double n2) {

        switch (operador) {
            case '+':
                Log.wtf("N1", String.valueOf(n1));
                Log.wtf("N2", String.valueOf(n2));

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