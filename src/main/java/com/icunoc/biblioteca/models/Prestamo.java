package com.icunoc.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.icunoc.biblioteca.enums.Idioma;

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

    @Column(name = "DPI")
    private int DPI;

    @Column(name = "carnet")
    private int carnet;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Calendar fechaInicio;

    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Calendar fechaFin;

    @Column(name = "costo")
    private double costo;

    @Column(name = "estado")
    private  int estado;

    @Column(name = "codigoReservacion")
    private String codigoReservacion;

    @Column(name = "codigoLibro")
    private String codigoLibro;

    public Prestamo(){}

    public Prestamo(int idPrestamo, String nombre, String apellido, int DPI, int carnet, String carrera, Calendar fechaInicio, Calendar fechaFin, double costo, int estado, String codigoReservacion, String codigoLibro) {
        this.id = idPrestamo;
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

    public int getIdPrestamo() {
        return id;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.id = idPrestamo;
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


//Prestamo.java
