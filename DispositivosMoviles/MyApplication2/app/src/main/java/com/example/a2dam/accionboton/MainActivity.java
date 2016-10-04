package com.example.a2dam.accionboton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button botonParaTexto = (Button) findViewById(R.id.BotonParaTexto);
        botonParaTexto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView vista = (TextView) findViewById(R.id.TextoCaja);
                vista.setText("Kappa");
            }
        });


    }
}