package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.models.AsignacionLibro;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class AsignacionLibroDtoTest {

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
        assertEquals(200, result, "Valor obtenido");
        System.out.println("id obtenido con exito");
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
        assertEquals(250, field.get(asignacionLibro), "Valores coinciden en set");
        System.out.println("id modificado con exito");
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
        assertEquals(200, result, "Valor obtenido");
        System.out.println("idCategoria obtenido con exito");
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
        assertEquals(250, field.get(asignacionLibro), "Valores coinciden en set");
        System.out.println("idCategoria modificado con exito");
    }

    @Test
    void getIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final AsignacionLibroDto asignacionLibro = new AsignacionLibroDto();
        final Field field = asignacionLibro.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(asignacionLibro, 200);

        //act
        final int result = asignacionLibro.getId();

        //assert
        assertEquals(200, result, "Valor obtenido");
        System.out.println("idLibro obtenido con exito");
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
        assertEquals(250, field.get(asignacionLibro), "Valores coinciden en set");
        System.out.println("idLibro modificado con exito");
    }
}