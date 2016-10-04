package com.example.a2dam.aplicacionbotontextoidioma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Declaracion de elementos
    Button botonTexto;
    TextView cajaTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonTexto=(Button)findViewById(R.id.botonParaTexto);
        botonTexto.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        cajaTexto=(TextView)findViewById(R.id.cajadetexto);
        cajaTexto.setText(R.string.hola);
    }
}
