package com.icunoc.biblioteca.dto;

import java.util.Calendar;

public class ComentarioDto {

    int id;
    String nombre;
    String identificacion;
    String comentario;
    Calendar fecha;
    int puntuacion;
    String idLibro;

    public ComentarioDto(int id, String nombre, String identificacion,String comentario, Calendar fecha, int puntuacion, String idLibro) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.comentario = comentario;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
        this.idLibro = idLibro;
    }

    public ComentarioDto() {}

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }
}
