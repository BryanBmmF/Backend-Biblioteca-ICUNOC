package com.icunoc.biblioteca.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idCategoria;
    @Column(nullable=false, length=100)
    private String nombre;
    @Column(nullable=true, length=250)
    private String descripcion;
    //Relacion con libros
    @OneToMany(mappedBy="categoria")
    private List<Libro> libros;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
