package com.example.xmlacda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xmlacda.utils.Analisis;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Partes extends AppCompatActivity {

    public static final String TEXTO = "<texto><uno>Hello World!</uno><dos>Goodbye</dos></texto>";
    public static final String ALUMNOS = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n" +
            "<alumnos>\n" +
            " <alumno>\n" +
            "    <nombre>Carmelo Cotón</nombre>\n" +
            " <nota asignatura=\"matemáticas\" fecha=\"15/11/2012\">7</nota>\n" +
            " <observaciones>bien</observaciones>\n" +
            " </alumno>\n" +
            " <alumno>\n" +
            "    <nombre>Aitor Tilla</nombre>\n" +
            " <nota asignatura=\"lengua\" fecha=\"20/11/2012\">3.4</nota>\n" +
            " <observaciones>regular</observaciones>\n" +
            " </alumno>\n" +
            " <alumno>\n" +
            "    <nombre>Mercedes Tartalada</nombre>\n" +
            " <nota asignatura=\"inglés\" fecha=\"24/11/2012\">9</nota>\n" +
            "    <observaciones>muy bien</observaciones>\n" +
            " </alumno>\n" +
            "</alumnos>";

    TextView txvInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partes);
        txvInformacion = (TextView) findViewById(R.id.txvInformacion);

        try {
            txvInformacion.setText(Analisis.analizar(ALUMNOS));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            txvInformacion.setText(e.getMessage());
        }
    }
}
