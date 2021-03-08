package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.enums.Idioma;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Calendar;

public class LibroDto {
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    private String autor;
    private String codigo;
    private int stock;
    private  int edicion;
    private Calendar fechaPublicacion;
    private Idioma rol;
    private String pathImagen;


    public LibroDto(){}

    public LibroDto(int id, String nombre, String autor, String codigo, int stock, int edicion, Calendar fechaPublicacion, Idioma rol, String pathImagen) {
        this.id=id;
        this.nombre = nombre;
        this.autor = autor;
        this.codigo = codigo;
        this.stock = stock;
        this.edicion = edicion;
        this.fechaPublicacion = fechaPublicacion;
        this.rol = rol;
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

    public Idioma getRol() {
        return rol;
    }

    public void setRol(Idioma rol) {
        this.rol = rol;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }
}
