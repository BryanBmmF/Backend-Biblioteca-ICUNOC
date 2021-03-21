package com.icunoc.biblioteca.dto;

import java.util.Date;

public class PrestamoDto {
    private int id;
    private String nombre;
    private String apellido;
    private int DPI;
    private int carnet;
    private String carrera;
    private Date fechaInicio;
    private Date fechaFin;
    private double costo;
    private int estado;
    private String codigoReservacion;
    private String codigoLibro;

    public PrestamoDto(){}

    public PrestamoDto(String nombre, String apellido, int DPI, int carnet, String carrera, Date fechaInicio, Date fechaFin, double costo, int estado, String codigoReservacion, String codigoLibro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DPI = DPI;
        this.carnet = carnet;
        this.carrera = carrera;
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

    public int getDPI() {
        return DPI;
    }

    public void setDPI(int DPI) {
        this.DPI = DPI;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
