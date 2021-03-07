package com.icunoc.biblioteca.dto;

/**
 * Clase para generar mensajes para envio al cliente
 * */

public class Mensaje {
    private String mensaje;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
