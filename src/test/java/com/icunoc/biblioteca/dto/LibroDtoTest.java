package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.enums.Idioma;
import com.icunoc.biblioteca.models.Libro;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class LibroDtoTest {

    @Test
    void getId() {
        LibroDto libro = new LibroDto();
        libro.setId(1);
        assertTrue(libro.getId() == 1);
    }

    @Test
    void setId() {
        LibroDto libro = new LibroDto();
        libro.setId(1);
        assertTrue(libro.getId() == 1);
    }

    @Test
    void getNombre() {
        LibroDto libro = new LibroDto();
        libro.setNombre("Nombre prueba");
        assertTrue(libro.getNombre() == "Nombre prueba");
    }

    @Test
    void setNombre() {
        LibroDto libro = new LibroDto();
        libro.setNombre("Nombre prueba");
        assertTrue(libro.getNombre() == "Nombre prueba");
    }

    /*
    @Test
    void getAutor() {
        LibroDto libro = new LibroDto();
        libro.setAutor("Autor");
        assertTrue(libro.getAutor()=="Autor");
    }

    @Test
    void setAutor() {
        LibroDto libro = new LibroDto();
        libro.setAutor("Autor");
        assertTrue(libro.getAutor() == "Autor");
    }*/

    @Test
    void getCodigo() {
        LibroDto libro = new LibroDto();
        libro.setCodigo("124Prueba");
        assertTrue(libro.getCodigo() == "124Prueba");
    }

    @Test
    void setCodigo() {
        LibroDto libro = new LibroDto();
        libro.setCodigo("124Prueba");
        assertTrue(libro.getCodigo() == "124Prueba");
    }

    @Test
    void getStock() {
        LibroDto libro = new LibroDto();
        libro.setStock(12);
        assertTrue(libro.getStock() == 12);
    }

    @Test
    void setStock() {
        LibroDto libro = new LibroDto();
        libro.setStock(12);
        assertTrue(libro.getStock() == 12);
    }

    @Test
    void getEdicion() {
        LibroDto libro = new LibroDto();
        libro.setEdicion(12);
        assertTrue(libro.getEdicion() == 12);
    }

    @Test
    void setEdicion() {
        LibroDto libro = new LibroDto();
        libro.setEdicion(12);
        assertTrue(libro.getEdicion() == 12);
    }

    @Test
    void getFechaPublicacion() {
        Calendar today = Calendar.getInstance();
        LibroDto libro = new LibroDto();
        libro.setFechaPublicacion(today);
        assertTrue(libro.getFechaPublicacion() == today);
    }

    @Test
    void setFechaPublicacion() {
        Calendar today = Calendar.getInstance();
        LibroDto libro = new LibroDto();
        libro.setFechaPublicacion(today);
        assertTrue(libro.getFechaPublicacion() == today);
    }

    @Test
    void getIdioma() {
        LibroDto libro = new LibroDto();
        libro.setIdioma(Idioma.ESPAÑOL);
        assertTrue(libro.getIdioma() == Idioma.ESPAÑOL);
    }

    @Test
    void setIdioma() {
        LibroDto libro = new LibroDto();
        libro.setIdioma(Idioma.ESPAÑOL);
        assertTrue(libro.getIdioma() == Idioma.ESPAÑOL);
    }

    @Test
    void getPathImagen() {
        byte[] imagenBytes = "bytes".getBytes();
        LibroDto libro = new LibroDto();
        libro.setPathImagen(imagenBytes);
        assertTrue(libro.getPathImagen() == imagenBytes);
    }

    @Test
    void setPathImagen() {
        byte[] imagenBytes = "bytes".getBytes();
        LibroDto libro = new LibroDto();
        libro.setPathImagen(imagenBytes);
        assertTrue(libro.getPathImagen() == imagenBytes);
    }

    @Test
    void getIdCategoria() {
        LibroDto libro = new LibroDto();
        libro.setIdCategoria(1);
        assertTrue(libro.getIdCategoria() == 1);
    }

    @Test
    void setIdCategoria() {
        LibroDto libro = new LibroDto();
        libro.setIdCategoria(1);
        assertTrue(libro.getIdCategoria() == 1);
    }
}
