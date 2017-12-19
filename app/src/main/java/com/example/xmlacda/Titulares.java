package com.example.xmlacda;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xmlacda.utils.Analisis;
import com.example.xmlacda.utils.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class Titulares extends AppCompatActivity implements View.OnClickListener{

    public static final String RSS = "https://www.alejandrosuarez.es/feed";
    //public static final String RSS = "http://10.0.2.2/feed/alejandro.xml";
    public static final String TEMPORAL = "alejandro.xml";

    private TextView txvTexto;
    private Button btnRss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulares);
        txvTexto = findViewById(R.id.txvTexto);
        btnRss = findViewById(R.id.btnRss);
        btnRss.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnRss){
            descarga(RSS,TEMPORAL);
        }
    }

    private void descarga(String rss, String temporal) {
        final ProgressDialog progreso = new ProgressDialog(this);
        File miFichero = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), temporal);
        RestClient.get(rss, new FileAsyncHttpResponseHandler(miFichero) {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(false);
                progreso.show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progreso.dismiss();
                //txvTexto.setText(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                progreso.dismiss();
                try {
                    txvTexto.setText(Analisis.analizarRSS(file));
                    Toast.makeText(getApplicationContext(),"Fichero descargado correctamente", Toast.LENGTH_SHORT).show();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    txvTexto.setText("Error: "+e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    txvTexto.setText("Error: "+e.getMessage());
                    Toast.makeText(getApplicationContext(),"Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}