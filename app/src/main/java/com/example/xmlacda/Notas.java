package com.example.xmlacda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xmlacda.utils.Analisis;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Notas extends AppCompatActivity {

    private TextView txvTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        txvTexto = findViewById(R.id.txvTexto);

        try {
            txvTexto.setText(Analisis.analizarNombres(getApplicationContext()));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            txvTexto.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            txvTexto.setText(e.getMessage());
        }
    }
}
