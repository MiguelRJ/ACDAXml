package com.example.acdaxmlclase.utils;

/**
 * Created by Miguel on 30/12/2017.
 */

public class Noticia {

    public static String NOTICIA = "NOTICIA";
    public static String URL = "URL";
    public static String DESCRIPCION = "DESCRIPCION";
    public static String FECHA = "FECHA";

    private String titulo;
    private String url;
    private String descripcion;
    private String fecha;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Noticia(String titulo, String url, String descripcion, String fecha) {
        this.titulo = titulo;
        this.url = url;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
}
