package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.enums.Idioma;
import java.util.Calendar;

public class LibroDto {
    private int id;
    private String nombre;
    private String autor;
    private String codigo;
    private int stock;
    private  int edicion;
    private Calendar fechaPublicacion;
    private Idioma idioma;
    private byte[] pathImagen;


    public LibroDto(){}

    public LibroDto(String autor, String codigo, int edicion, Calendar fechaPublicacion, Idioma idioma, String nombre, byte[] pathImagen, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.autor = autor;
        this.stock = stock;
        this.edicion = edicion;
        this.fechaPublicacion = fechaPublicacion;
        this.idioma = idioma;
        this.pathImagen = pathImagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public byte[] getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(byte[] pathImagen) {
        this.pathImagen = pathImagen;
    }

}
