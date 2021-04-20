package com.icunoc.biblioteca.models;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class AsignacionLibroTest {
    private static final String SUCCESS = "Valor obtenido ";
    @Test
    void getIdAsignacionLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibro asignacionLibro = new AsignacionLibro();
        final Field field = asignacionLibro.getClass().getDeclaredField("idAsignacionLibro");
        field.setAccessible(true);
        field.set(asignacionLibro, 200);

        //act
        final int result = asignacionLibro.getIdAsignacionLibro();

        //assert
        assertEquals(200, result, SUCCESS);
    }

    @Test
    void setIdAsignacionLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibro asignacionLibro = new AsignacionLibro();

        //act
        asignacionLibro.setIdAsignacionLibro(250);

        //assert
        final Field field = asignacionLibro.getClass().getDeclaredField("idAsignacionLibro");
        field.setAccessible(true);
        assertEquals(250, field.get(asignacionLibro), SUCCESS);

    }

    @Test
    void getIdCategoria() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibro asignacionLibro = new AsignacionLibro();
        final Field field = asignacionLibro.getClass().getDeclaredField("idCategoria");
        field.setAccessible(true);
        field.set(asignacionLibro, 200);

        //act
        final int result = asignacionLibro.getIdCategoria();

        //assert
        assertEquals(200, result, SUCCESS);

    }

    @Test
    void setIdCategoria() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibro asignacionLibro = new AsignacionLibro();

        //act
        asignacionLibro.setIdCategoria(250);

        //assert
        final Field field = asignacionLibro.getClass().getDeclaredField("idCategoria");
        field.setAccessible(true);
        assertEquals(250, field.get(asignacionLibro), SUCCESS);

    }

    @Test
    void getIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibro asignacionLibro = new AsignacionLibro();
        final Field field = asignacionLibro.getClass().getDeclaredField("idLibro");
        field.setAccessible(true);
        field.set(asignacionLibro, 200);

        //act
        final int result = asignacionLibro.getIdLibro();

        //assert
        assertEquals(200, result, SUCCESS);

    }

    @Test
    void setIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibro asignacionLibro = new AsignacionLibro();

        //act
        asignacionLibro.setIdLibro(250);

        //assert
        final Field field = asignacionLibro.getClass().getDeclaredField("idLibro");
        field.setAccessible(true);
        assertEquals(250, field.get(asignacionLibro), SUCCESS);

    }
}