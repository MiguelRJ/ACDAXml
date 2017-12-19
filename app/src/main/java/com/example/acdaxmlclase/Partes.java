package com.example.acdaxmlclase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.acdaxmlclase.utils.Analisis;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Partes extends AppCompatActivity {

    public static final String TEXTO = "<texto><uno>Hello World!</uno><dos>Goodbye</dos></texto>";
    XmlPullParser alumnos;

    TextView txvInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partes);
        alumnos = getApplicationContext().getResources().getXml(R.xml.alumnos);
        txvInformacion = (TextView) findViewById(R.id.txvInformacion);

        try {
            txvInformacion.setText(Analisis.analizarXML(alumnos));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            txvInformacion.setText(e.getMessage());
        }
    }

}
