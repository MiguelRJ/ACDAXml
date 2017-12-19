package com.example.xmlacda.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Xml;

import com.example.xmlacda.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by usuario on 12/12/17.
 */

public class Analisis {

    public static String analizarTexto(String texto) throws XmlPullParserException, IOException
    {
        StringBuilder cadena = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();

        xpp.setInput( new StringReader(texto));
        int eventType = xpp.getEventType();
        cadena.append("Inicio . . . \n");

        while (eventType != XmlPullParser. END_DOCUMENT ) {

            if(eventType == XmlPullParser.START_DOCUMENT){
                cadena.append("START_DOCUMENT"+"\n");
            } else if (eventType == XmlPullParser.START_TAG){
                cadena.append("START_TAG:" + xpp.getName() + "\n");
            } else if (eventType == XmlPullParser.TEXT){
                cadena.append("TEXT:" + xpp.getText() + "\n");
            } else if (eventType == XmlPullParser.END_TAG){
                cadena.append("END_TAG:" + xpp.getName() + "\n");
            }
            eventType = xpp.next();

        }
        //System.out.println("End document");
        cadena.append("End document" + "\n" + "Fin");
        return cadena.toString();
    }

    public static String analizarXML(XmlPullParser xpp) throws XmlPullParserException, IOException
    {
        StringBuilder cadena = new StringBuilder();

        int eventType = xpp.getEventType();
        cadena.append("Inicio . . . \n");

        while (eventType != XmlPullParser. END_DOCUMENT ) {

            if(eventType == XmlPullParser.START_DOCUMENT){
                cadena.append("START_DOCUMENT"+"\n");
            } else if (eventType == XmlPullParser.START_TAG){
                cadena.append("START_TAG:" + xpp.getName() + "\n");
            } else if (eventType == XmlPullParser.TEXT){
                cadena.append("TEXT:" + xpp.getText() + "\n");
            } else if (eventType == XmlPullParser.END_TAG){
                cadena.append("END_TAG:" + xpp.getName() + "\n");
            }
            eventType = xpp.next();

        }
        //System.out.println("End document");
        cadena.append("End document" + "\n" + "Fin");
        return cadena.toString();
    }

    public static String analizarNombres(Context c) throws XmlPullParserException, IOException {

        boolean esNombre = false;
        boolean esNota = false;
        StringBuilder cadena = new StringBuilder();

        Double notasTotal = 0.0;
        int numAlumnos = 0;

        XmlResourceParser xrp = c.getResources().getXml(R.xml.alumnos);
        int eventType = xrp.getEventType();

        while (eventType != XmlPullParser. END_DOCUMENT ) {
            switch (eventType) {
                case XmlPullParser.START_TAG :
                    if (xrp.getName().equals("nombre")){
                        esNombre = true;
                    }
                    if (xrp.getName().equals("nota")){
                        if(xrp.getAttributeCount() > 0){ // si hay atributos ATT
                            for (int i = 0; i < xrp.getAttributeCount(); i++) { // recorrer atributos
                                cadena.append(xrp.getAttributeName(i)+": "+xrp.getAttributeValue(i) + "\n");
                            }
                        }
                        esNota = true;
                    }
                    break;
                case XmlPullParser.TEXT :
                    if (esNombre){
                        numAlumnos++;
                        cadena.append("Alumno: "+xrp.getText() + "\n");
                    }
                    if(esNota){
                        notasTotal += Double.parseDouble(xrp.getText());
                        cadena.append("Nota: "+xrp.getText() + "\n");
                    }
                    break;
                case XmlPullParser.END_TAG :
                    if (xrp.getName().equals("alumno")) {
                        cadena.append("\n");
                    }
                    esNombre = false;
                    esNota = false;
                    break;
            }
            eventType = xrp.next();
        }

        cadena.append("Alumnos: " + numAlumnos + "\n");
        cadena.append("Media: " + String.format("%.2f",notasTotal/numAlumnos));

        return cadena.toString();
    }

}
