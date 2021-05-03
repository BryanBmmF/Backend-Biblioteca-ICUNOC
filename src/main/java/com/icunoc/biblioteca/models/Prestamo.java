package com.icunoc.biblioteca.models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dpi")
    private String dpi;

    @Column(name = "carnet")
    private String carnet;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "fechaReservacion")
    @Temporal(TemporalType.DATE)
    private Calendar fechaReservacion;

    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Calendar fechaInicio;

    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Calendar fechaFin;

    @Column(name = "costo")
    private double costo;

    @Column(name = "estado")
    private  String estado;

    @Column(name = "codigoReservacion")
    private String codigoReservacion;

    @Column(name = "mora")
    private boolean mora;

    @Column(name = "diasMoroso")
    private int diasMoroso;

    @Column(name = "codigoLibro")
    private String codigoLibro;

    public Prestamo(){}

    public Prestamo(String nombre, String apellido, String dpi, String carnet, String carrera, Calendar fechaReservacion, Calendar fechaInicio, Calendar fechaFin,  String estado, String codigoReservacion, boolean mora, String codigoLibro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dpi = dpi;
        this.carnet = carnet;
        this.carrera = carrera;
        this.fechaReservacion = fechaReservacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.codigoReservacion = codigoReservacion;
        this.mora=mora;
        this.codigoLibro = codigoLibro;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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

    public boolean isMora() {
        return mora;
    }

    public void setMora(boolean mora) {
        this.mora = mora;
    }

    public int getDiasMoroso() { return diasMoroso; }

    public void setDiasMoroso(int diasMoroso) { this.diasMoroso = diasMoroso; }
}


//Prestamo.java
