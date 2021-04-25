package com.icunoc.biblioteca.dto;

/**
 * Clase para generar mensajes para envio al cliente
 * */

public class Mensaje {
    private String info;

    public Mensaje(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
