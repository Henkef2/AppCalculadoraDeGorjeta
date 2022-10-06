package com.henkef.app6calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorcentagem;
    private TextView textGorjeta;
    private TextView textTotal;
    private SeekBar seekBarGorjeta;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);

        //Adicionar listner SeekBar
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                porcentagem = progress;
                textPorcentagem.setText( Math.round(porcentagem) + " %");  //o comando Math.round transforma os numeros decimais em numeros arredondados sem virgula
                calcular();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){
        String valorRecuperado = editValor.getText().toString();
        if ( valorRecuperado == null || valorRecuperado.equals("") ){

            Toast.makeText(getApplicationContext(), "Digite um valor primeiro!", Toast.LENGTH_LONG).show();

        } else {

            //Converter string para double
            double valorDigitado = Double.parseDouble( valorRecuperado ); // esse comando converte de Sting para Double para poder usar em calculos

            //Calcula a gorjeta total
            double gorjeta = valorDigitado * (porcentagem/100);
            double total = gorjeta + valorDigitado;

            //exibe a gorjeta
            textGorjeta.setText("R$ " + Math.round(gorjeta) );  //exibe o valor da gorjeta porem com varias casas decimais, então foi utilizado o Math.round para arredondar os valores
            textTotal.setText("R$ " + total);
        }
    }

}