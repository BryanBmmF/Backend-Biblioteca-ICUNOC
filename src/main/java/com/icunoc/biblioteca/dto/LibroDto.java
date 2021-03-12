package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.enums.Idioma;
import com.icunoc.biblioteca.models.Categoria;
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
    private int categoria;


    public LibroDto(){}

    public LibroDto(String autor, String codigo, int edicion, Calendar fechaPublicacion, Idioma idioma, String nombre, String pathImagen,int stock,  int categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.autor = autor;
        this.stock = stock;
        this.edicion = edicion;
        this.fechaPublicacion = fechaPublicacion;
        this.rol = idioma;
        this.pathImagen = pathImagen;
        this.categoria = categoria;
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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
