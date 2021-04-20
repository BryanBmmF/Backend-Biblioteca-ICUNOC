package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.enums.Idioma;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class LibroDtoTest {

    private static final byte[] BYTES_IMAGEN = "bytes".getBytes();
    private static final String NOMBRE_TEST = "Nombre";
    private static final String CODIGO_TEST = "12345";

    @Test
    void constructor() {
        //act
        byte[] imagenBytes = BYTES_IMAGEN;
        LibroDto libroDto = new LibroDto("Autor", "codigo", 1, Calendar.getInstance(), Idioma.ESPAÑOL, "Nombre", imagenBytes, 1);
        assertTrue(libroDto.getIdioma() == Idioma.ESPAÑOL);
    }

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
        libro.setNombre(NOMBRE_TEST);
        assertTrue(libro.getNombre() == NOMBRE_TEST);
    }

    @Test
    void setNombre() {
        LibroDto libro = new LibroDto();
        libro.setNombre(NOMBRE_TEST);
        assertTrue(libro.getNombre() == NOMBRE_TEST);
    }

    @Test
    void getCodigo() {
        LibroDto libro = new LibroDto();
        libro.setCodigo(CODIGO_TEST);
        assertTrue(libro.getCodigo() == CODIGO_TEST);
    }

    @Test
    void setCodigo() {
        LibroDto libro = new LibroDto();
        libro.setCodigo(CODIGO_TEST);
        assertTrue(libro.getCodigo() == CODIGO_TEST);
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
        byte[] imagenBytes = BYTES_IMAGEN;
        LibroDto libro = new LibroDto();
        libro.setPathImagen(imagenBytes);
        assertTrue(libro.getPathImagen() == imagenBytes);
    }

    @Test
    void setPathImagen() {
        byte[] imagenBytes = BYTES_IMAGEN;
        LibroDto libro = new LibroDto();
        libro.setPathImagen(imagenBytes);
        assertTrue(libro.getPathImagen() == imagenBytes);
    }

}
