package com.icunoc.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "asignacion_libro")
public class AsignacionLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idAsignacionLibro;
    @Column(name = "idCategoria")
    private int idCategoria;
    @Column(name = "idLibro")
    private int idLibro;

    public AsignacionLibro() {}

    public AsignacionLibro(int idAsignacionLibro, int idCategoria, int idLibro) {
        this.idAsignacionLibro = idAsignacionLibro;
        this.idCategoria = idCategoria;
        this.idLibro = idLibro;
    }

    public int getIdAsignacionLibro() {
        return idAsignacionLibro;
    }

    public void setIdAsignacionLibro(int idAsignacionLibro) {
        this.idAsignacionLibro = idAsignacionLibro;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
}
