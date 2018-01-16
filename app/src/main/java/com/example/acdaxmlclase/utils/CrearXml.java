package com.example.acdaxmlclase.utils;

import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by usuario on 16/01/18.
 */

public class CrearXml {

    public static String TITULO = "titulo";
    public static String FECHA = "fecha";
    public static String URL = "url";
    public static String DESCIPCION = "descripcion";

    public static void crearXML(ArrayList<Noticia> noticias, String fichero) throws IOException {
        FileOutputStream fout;
        fout = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fichero));
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(fout, "UTF-8");
        serializer.startDocument(null, true);
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true); //poner tabulación
        serializer.startTag(null, "titulares");
        for (int i = 0; i < noticias.size(); i++) {
            serializer.startTag(null,CrearXml.TITULO);
            serializer.attribute(null,CrearXml.FECHA,noticias.get(i).getFecha());
            serializer.text(noticias.get(i).getTitulo());
            serializer.endTag(null,CrearXml.TITULO);

            serializer.startTag(null,CrearXml.URL);
            serializer.text(noticias.get(i).getUrl());
            serializer.endTag(null,CrearXml.URL);

            serializer.startTag(null,CrearXml.DESCIPCION);
            serializer.text(noticias.get(i).getDescripcion());
            serializer.endTag(null,CrearXml.DESCIPCION);
        }
        serializer.endTag(null, "titulares");
        serializer.endDocument();
        serializer.flush();
        fout.close();
    }
}
