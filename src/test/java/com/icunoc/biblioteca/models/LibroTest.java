package com.icunoc.biblioteca.models;

import com.icunoc.biblioteca.dto.LibroDto;
import com.icunoc.biblioteca.enums.Idioma;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    @Test
    void getIdLibro() {
        Libro libro = new Libro();
        libro.setIdLibro(1);
        assertTrue(libro.getIdLibro() == 1);
    }

    @Test
    void setIdLibro() {
        Libro libro = new Libro();
        libro.setIdLibro(1);
        assertTrue(libro.getIdLibro() == 1);
    }

    @Test
    void getCodigo() {
        Libro libro = new Libro();
        libro.setCodigo("124Prueba");
        assertTrue(libro.getCodigo() == "124Prueba");
    }

    @Test
    void setCodigo() {
        Libro libro = new Libro();
        libro.setCodigo("124Prueba");
        assertTrue(libro.getCodigo() == "124Prueba");
    }

    @Test
    void getNombre() {
        Libro libro = new Libro();
        libro.setNombre("124Prueba");
        assertTrue(libro.getNombre() == "124Prueba");
    }

    @Test
    void setNombre() {
        Libro libro = new Libro();
        libro.setNombre("124Prueba");
        assertTrue(libro.getNombre() == "124Prueba");
    }

    @Test
    void getAutor() {
        Libro libro = new Libro();
        libro.setAutor("124Prueba");
        assertTrue(libro.getAutor() == "124Prueba");
    }

    @Test
    void setAutor() {
        Libro libro = new Libro();
        libro.setAutor("124Prueba");
        assertTrue(libro.getAutor() == "124Prueba");
    }

    @Test
    void getStock() {
        Libro libro = new Libro();
        libro.setStock(1);
        assertTrue(libro.getStock() == 1);
    }

    @Test
    void setStock() {
        Libro libro = new Libro();
        libro.setStock(1);
        assertTrue(libro.getStock() == 1);
    }

    @Test
    void getEdicion() {
        Libro libro = new Libro();
        libro.setEdicion(1);
        assertTrue(libro.getEdicion() == 1);
    }

    @Test
    void setEdicion() {
        Libro libro = new Libro();
        libro.setEdicion(1);
        assertTrue(libro.getEdicion() == 1);
    }

    @Test
    void getFechaPublicacion() {
        Calendar today = Calendar.getInstance();
        Libro libro = new Libro();
        libro.setFechaPublicacion(today);
        assertTrue(libro.getFechaPublicacion() == today);
    }

    @Test
    void setFechaPublicacion() {
        Calendar today = Calendar.getInstance();
        Libro libro = new Libro();
        libro.setFechaPublicacion(today);
        assertTrue(libro.getFechaPublicacion() == today);
    }

    @Test
    void getIdioma() {
        Libro libro = new Libro();
        libro.setIdioma(Idioma.INGLES);
        assertTrue(libro.getIdioma() == Idioma.INGLES);
    }

    @Test
    void setIdioma() {
        Libro libro = new Libro();
        libro.setIdioma(Idioma.INGLES);
        assertTrue(libro.getIdioma() == Idioma.INGLES);
    }

    @Test
    void getPathImagen() {
        byte[] imagenBytes = "bytes".getBytes();
        Libro libro = new Libro();
        libro.setPathImagen(imagenBytes);
        assertTrue(libro.getPathImagen() == imagenBytes);
    }

    @Test
    void setPathImagen() {
        byte[] imagenBytes = "bytes".getBytes();
        Libro libro = new Libro();
        libro.setPathImagen(imagenBytes);
        assertTrue(libro.getPathImagen() == imagenBytes);
    }

    @Test
    void getCategoria() {
        Libro libro = new Libro();
        libro.setIdCategoria(1);
        assertTrue(libro.getIdCategoria() == 1);
    }

    @Test
    void setCategoria() {
        Libro libro = new Libro();
        libro.setIdCategoria(1);
        assertTrue(libro.getIdCategoria() == 1);
    }
}
