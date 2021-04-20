package com.icunoc.biblioteca.models;

import com.icunoc.biblioteca.enums.Idioma;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    private static final String CODIGO_LIBRO_TEST = "123ABC";

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
        libro.setCodigo(CODIGO_LIBRO_TEST);
        assertTrue(libro.getCodigo() == CODIGO_LIBRO_TEST);
    }

    @Test
    void setCodigo() {
        Libro libro = new Libro();
        libro.setCodigo(CODIGO_LIBRO_TEST);
        assertTrue(libro.getCodigo() == CODIGO_LIBRO_TEST);
    }

    @Test
    void getNombre() {
        Libro libro = new Libro();
        libro.setNombre(CODIGO_LIBRO_TEST);
        assertTrue(libro.getNombre() == CODIGO_LIBRO_TEST);
    }

    @Test
    void setNombre() {
        Libro libro = new Libro();
        libro.setNombre(CODIGO_LIBRO_TEST);
        assertTrue(libro.getNombre() == CODIGO_LIBRO_TEST);
    }

    @Test
    void getAutor() {
        Libro libro = new Libro();
        libro.setAutor(CODIGO_LIBRO_TEST);
        assertTrue(libro.getAutor() == CODIGO_LIBRO_TEST);
    }

    @Test
    void setAutor() {
        Libro libro = new Libro();
        libro.setAutor(CODIGO_LIBRO_TEST);
        assertTrue(libro.getAutor() == CODIGO_LIBRO_TEST);
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

}
