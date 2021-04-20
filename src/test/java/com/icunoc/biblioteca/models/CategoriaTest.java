package com.icunoc.biblioteca.models;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {
    private static final String SUCCESS = "Valor obtenido ";
    @Test
    void getIdCategoria_setsProperly() throws NoSuchFieldException, IllegalAccessException{
        //arrange
        final Categoria categoria = new Categoria();
        final Field field = categoria.getClass().getDeclaredField("idCategoria");
        field.setAccessible(true);
        field.set(categoria, 158);

        //act
        final int result = categoria.getIdCategoria();

        //assert
        assertEquals(158, result, SUCCESS);

    }

    @Test
    void setIdCategoria_getsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Categoria categoria = new Categoria();

        //act
        categoria.setIdCategoria(1);

        //assert
        final Field field = categoria.getClass().getDeclaredField("idCategoria");
        field.setAccessible(true);
        assertEquals(1, field.get(categoria), SUCCESS);

    }

    @Test
    void getNombre_getsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Categoria categoria = new Categoria();
        final Field field = categoria.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        field.set(categoria, "Matematica");

        //act
        final String result = categoria.getNombre();

        //assert
        assertEquals("Matematica", result, SUCCESS);

    }

    @Test
    void setNombre_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Categoria categoria = new Categoria();

        //act
        categoria.setNombre("Matematica");

        //assert
        final Field field = categoria.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        assertEquals("Matematica", field.get(categoria), SUCCESS);

    }

    @Test
    void getDescripcion_getsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Categoria categoria = new Categoria();
        final Field field = categoria.getClass().getDeclaredField("descripcion");
        field.setAccessible(true);
        field.set(categoria, "Descripcion Categoria");

        //act
        final String result = categoria.getDescripcion();

        //assert
        assertEquals("Descripcion Categoria", result, SUCCESS);

    }

    @Test
    void setDescripcion_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Categoria categoria = new Categoria();
        //act
        categoria.setDescripcion("Descripcion Categoria");

        //assert
        final Field field = categoria.getClass().getDeclaredField("descripcion");
        field.setAccessible(true);
        assertEquals("Descripcion Categoria", field.get(categoria), SUCCESS);

    }
}