package com.icunoc.biblioteca.models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idComentario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "puntuacion")
    private int puntuacion;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Calendar fecha;
    @Column(name = "idLibro")
    private String idLibro;

    public Comentario(int idComentario, String nombre, int puntuacion, String comentario, Calendar fecha, String idLibro) {
        this.idComentario = idComentario;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.idLibro = idLibro;
    }

    public Comentario() {}

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
}
