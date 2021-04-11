package com.icunoc.biblioteca.models;

import javax.persistence.*;

@Entity
@Table(name = "Info_Biblioteca")
public class InfoBiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "horario")
    private String horario;

    @Column(name = "diasHabilesPrestamo")
    private int diasHabilesPrestamo;

    @Column(name = "costoDiaMoroso")
    private float costoDiaMoroso;

    @Column(name = "costoGeneralPrestamo")
    private float costoGeneralPrestamo;

    public InfoBiblioteca() {
    }

    public InfoBiblioteca(String correo, String direccion, String telefono, String horario, int diasHabilesPrestamo, float costoDiaMoroso, float costoGeneralPrestamo) {
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horario = horario;
        this.diasHabilesPrestamo = diasHabilesPrestamo;
        this.costoDiaMoroso = costoDiaMoroso;
        this.costoGeneralPrestamo = costoGeneralPrestamo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getDiasHabilesPrestamo() {
        return diasHabilesPrestamo;
    }

    public void setDiasHabilesPrestamo(int diasHabilesPrestamo) {
        this.diasHabilesPrestamo = diasHabilesPrestamo;
    }

    public float getCostoDiaMoroso() {
        return costoDiaMoroso;
    }

    public void setCostoDiaMoroso(float costoDiaMoroso) {
        this.costoDiaMoroso = costoDiaMoroso;
    }

    public float getCostoGeneralPrestamo() {
        return costoGeneralPrestamo;
    }

    public void setCostoGeneralPrestamo(float costoGeneralPrestamo) {
        this.costoGeneralPrestamo = costoGeneralPrestamo;
    }
}

