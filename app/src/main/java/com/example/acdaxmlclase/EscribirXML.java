package com.example.acdaxmlclase;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.acdaxmlclase.utils.CrearXml;
import com.example.acdaxmlclase.utils.Noticia;
import com.example.acdaxmlclase.utils.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class EscribirXML extends AppCompatActivity implements View.OnClickListener {

    public static final String RSS = "http://www.alejandrosuarez.es/feed/";
    //public static final String RSS = "http://10.0.2.2/feed/alejandro.xml";
    public static final String TEMPORAL = "alejandro.xml";
    public static final String FICHERO_XML = "resultado.xml";
    Button boton;
    ArrayList<Noticia> noticias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_xml);
        boton = (Button) findViewById(R.id.btnDescargar);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == boton)
            descarga(RSS, TEMPORAL);
    }

    private void descarga(String rss, String temporal) {
        final ProgressDialog progress = new ProgressDialog(this);
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), temporal);//mrj
        RestClient.get(RSS, new FileAsyncHttpResponseHandler(file) {
            @Override
            public void onStart() {
                super.onStart();
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setMessage("Conectando . . .");
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progress.dismiss();
                showError("Error: "+String.valueOf(statusCode));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                progress.dismiss();
                toastShort("Fichero descargado correctamente");
                leerFichero(file);
            }
        });
    }

    public void leerFichero(File file){
        try {
            CrearXml.crearXML(noticias,FICHERO_XML);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error: "+e.getMessage());
        }
    }

    /**
     * Metodo para hacer un toast corto para que el codigo sea mas facil
     * @param message
     */
    public void toastShort(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * Para mostrar errores hara un Toast y aparte mostrara el error en pantalla
     * @param message
     */
    public void showError(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
