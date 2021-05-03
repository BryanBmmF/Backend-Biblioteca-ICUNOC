package com.icunoc.biblioteca.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ComentarioTest {

    private static final String SUCCESS = "Valor obtenido ";
    @BeforeEach
    void setUp() {
    }

    @Test
    void getIdComentario() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();
        final Field field = comentario.getClass().getDeclaredField("idComentario");
        field.setAccessible(true);
        field.set(comentario, 200);

        //act
        final int result = comentario.getIdComentario();

        //assert
        assertEquals(200, result, SUCCESS);
    }

    @Test
    void setIdComentario() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();

        //act
        comentario.setIdComentario(250);

        //assert
        final Field field = comentario.getClass().getDeclaredField("idComentario");
        field.setAccessible(true);
        assertEquals(250, field.get(comentario), SUCCESS);

    }

    @Test
    void getNombre() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();
        final Field field = comentario.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        field.set(comentario, "comentariotest");

        //act
        final String result = comentario.getNombre();

        //assert
        assertEquals("comentariotest", result, SUCCESS);
    }

    @Test
    void setNombre() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();

        //act
        comentario.setNombre("TEST");

        //assert
        final Field field = comentario.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        assertEquals("TEST", field.get(comentario), SUCCESS);

    }

    @Test
    void getPuntuacion() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();
        final Field field = comentario.getClass().getDeclaredField("puntuacion");
        field.setAccessible(true);
        field.set(comentario, 5);

        //act
        final int result = comentario.getPuntuacion();

        //assert
        assertEquals(5, result, SUCCESS);
    }

    @Test
    void setPuntuacion() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();

        //act
        comentario.setPuntuacion(250);

        //assert
        final Field field = comentario.getClass().getDeclaredField("puntuacion");
        field.setAccessible(true);
        assertEquals(250, field.get(comentario), SUCCESS);

    }

    @Test
    void getComentario() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();
        final Field field = comentario.getClass().getDeclaredField("comentario");
        field.setAccessible(true);
        field.set(comentario, "comentariotest");

        //act
        final String result = comentario.getComentario();

        //assert
        assertEquals("comentariotest", result, SUCCESS);
    }

    @Test
    void setComentario() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();

        //act
        comentario.setComentario("COMENTARIOTEST");

        //assert
        final Field field = comentario.getClass().getDeclaredField("comentario");
        field.setAccessible(true);
        assertEquals("COMENTARIOTEST", field.get(comentario), SUCCESS);

    }

    @Test
    void getFecha() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();
        final Field field = comentario.getClass().getDeclaredField("fecha");
        final Calendar date = Calendar.getInstance();
        field.setAccessible(true);
        field.set(comentario, date);

        //act
        final Calendar result = comentario.getFecha();

        //assert
        assertEquals(date, result, SUCCESS);
    }

    @Test
    void setFecha() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();
        final Calendar date = Calendar.getInstance();
        //act
        comentario.setFecha(date);

        //assert
        final Field field = comentario.getClass().getDeclaredField("fecha");
        field.setAccessible(true);
        assertEquals(date, field.get(comentario), SUCCESS);

    }

    @Test
    void getIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();
        final Field field = comentario.getClass().getDeclaredField("idLibro");
        field.setAccessible(true);
        field.set(comentario, "12");

        //act
        final String result = comentario.getIdLibro();

        //assert
        assertEquals("12", result, SUCCESS);
    }

    @Test
    void setIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Comentario comentario = new Comentario();

        //act
        comentario.setIdLibro("250");

        //assert
        final Field field = comentario.getClass().getDeclaredField("idLibro");
        field.setAccessible(true);
        assertEquals("250", field.get(comentario), SUCCESS);

    }


}