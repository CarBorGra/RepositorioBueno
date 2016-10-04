package com.example.a2dam.radiobuttonmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("kappa");
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioOpcion1:
                if (checked)
                    // Primera opcion
                    textView.setText("Has elegido la opcion 1");
                break;
            case R.id.radioOpcion2:
                if (checked)
                    // Segunda opcion
                    textView.setText("Has elegido la opcion 2");
                break;
            case R.id.radioOpcion3:
                if (checked)
                    // Tercera opcion
                    textView.setText("Has elegido la opcion 3");
                break;
            case R.id.radioOpcion4:
                if (checked)
                    // Cuarta opcion
                    textView.setText("Has elegido la opcion 4");
                break;
        }
    }
}
