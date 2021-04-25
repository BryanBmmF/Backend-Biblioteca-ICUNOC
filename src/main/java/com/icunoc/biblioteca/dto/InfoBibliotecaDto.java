package com.icunoc.biblioteca.dto;

public class InfoBibliotecaDto {

    private String correo;
    private String direccion;
    private String telefono;
    private String horario;
    private int diasHabilesPrestamo;
    private float costoDiaMoroso;
    private float costoGeneralPrestamo;

    public InfoBibliotecaDto() {
    }

    public InfoBibliotecaDto(String correo, String direccion, String telefono, String horario, int diasHabilesPrestamo, float costoDiaMoroso, float costoGeneralPrestamo) {
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horario = horario;
        this.diasHabilesPrestamo = diasHabilesPrestamo;
        this.costoDiaMoroso = costoDiaMoroso;
        this.costoGeneralPrestamo = costoGeneralPrestamo;
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
