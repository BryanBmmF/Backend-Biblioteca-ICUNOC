package com.icunoc.biblioteca.dto;

public class AsignacionLibroDto {
    private int id;
    private int idCategoria;
    private int idLibro;

    public AsignacionLibroDto(int id, int idCategoria, int idLibro) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.idLibro = idLibro;
    }

    public AsignacionLibroDto () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
}
