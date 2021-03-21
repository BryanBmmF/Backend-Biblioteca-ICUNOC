package com.icunoc.biblioteca.dto;

public class UserDto {
    private String nombre;
    private String numeroRegistro;
    private String username;
    private String password;
    private String tipo;

    public UserDto() {
    }

    public UserDto(String nombre, String numeroRegistro, String username, String password, String tipo) {
        this.nombre = nombre;
        this.numeroRegistro = numeroRegistro;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
