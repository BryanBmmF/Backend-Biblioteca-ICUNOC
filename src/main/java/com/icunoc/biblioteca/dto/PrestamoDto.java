package com.icunoc.biblioteca.dto;

import java.util.Calendar;
import java.util.Date;

public class PrestamoDto {
    private int id;
    private String nombre;
    private String apellido;
    private String dpi;
    private String carnet;
    private String carrera;
    private Calendar fechaReservacion;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private double costo;
    private String estado;
    private String codigoReservacion;
    private String codigoLibro;

    public PrestamoDto(){}

    public PrestamoDto(String nombre, String apellido, String dpi, String carnet, String carrera, Calendar fechaReservacion, Calendar fechaInicio, Calendar fechaFin, double costo, String estado, String codigoReservacion, String codigoLibro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dpi = dpi;
        this.carnet = carnet;
        this.carrera = carrera;
        this.fechaReservacion = fechaReservacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.estado = estado;
        this.codigoReservacion = codigoReservacion;
        this.codigoLibro = codigoLibro;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public Calendar getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Calendar fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoReservacion() {
        return codigoReservacion;
    }

    public void setCodigoReservacion(String codigoReservacion) {
        this.codigoReservacion = codigoReservacion;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }
}
