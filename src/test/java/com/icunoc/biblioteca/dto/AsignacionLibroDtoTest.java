package com.icunoc.biblioteca.dto;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class AsignacionLibroDtoTest {

    private static final String success = "Valor obtenido ";
    @Test
    void constructor() {
        //act
        AsignacionLibroDto asignacion = new AsignacionLibroDto(1,2,3);
        //assert
        assertEquals(1,asignacion.getId());
        assertEquals(2,asignacion.getIdCategoria());
        assertEquals(3,asignacion.getIdLibro());

    }
    @Test
    void getId() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibroDto asignacionLibro = new AsignacionLibroDto();
        final Field field = asignacionLibro.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(asignacionLibro, 200);

        //act
        final int result = asignacionLibro.getId();

        //assert
        assertEquals(200, result, success);
    }

    @Test
    void setId() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibroDto asignacionLibro = new AsignacionLibroDto();

        //act
        asignacionLibro.setId(250);

        //assert
        final Field field = asignacionLibro.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(250, field.get(asignacionLibro), success);
    }

    @Test
    void getIdCategoria() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibroDto asignacionLibro = new AsignacionLibroDto();
        final Field field = asignacionLibro.getClass().getDeclaredField("idCategoria");
        field.setAccessible(true);
        field.set(asignacionLibro, 200);

        //act
        final int result = asignacionLibro.getIdCategoria();

        //assert
        assertEquals(200, result, success);
    }

    @Test
    void setIdCategoria() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibroDto asignacionLibro = new AsignacionLibroDto();

        //act
        asignacionLibro.setIdCategoria(250);

        //assert
        final Field field = asignacionLibro.getClass().getDeclaredField("idCategoria");
        field.setAccessible(true);
        assertEquals(250, field.get(asignacionLibro), success);
    }

    @Test
    void getIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibroDto asignacionLibro = new AsignacionLibroDto();
        final Field field = asignacionLibro.getClass().getDeclaredField("idLibro");
        field.setAccessible(true);
        field.set(asignacionLibro, 200);

        //act
        final int result = asignacionLibro.getIdLibro();

        //assert
        assertEquals(200, result, success);
    }

    @Test
    void setIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibroDto asignacionLibro = new AsignacionLibroDto();

        //act
        asignacionLibro.setIdLibro(250);

        //assert
        final Field field = asignacionLibro.getClass().getDeclaredField("idLibro");
        field.setAccessible(true);
        assertEquals(250, field.get(asignacionLibro), success);
    }
}