package com.icunoc.biblioteca.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaDtoTest {

    private static final String success = "Valor obtenido ";
    private CategoriaDto categoriaDto;
    @Test
    void constructor() {
        //act
        categoriaDto = new CategoriaDto(1,"algo","algomas");
        //assert
        Assertions.assertEquals(1,categoriaDto.getId());
    }

    @Test
    void getId() throws IllegalAccessException, NoSuchFieldException {
        //arrange
        final CategoriaDto categoria = new CategoriaDto();
        final Field field = categoria.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(categoria, 158);

        //act
        final int result = categoria.getId();

        //assert
        assertEquals(158, result, success);
    }

    @Test
    void setId_getsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final CategoriaDto categoria = new CategoriaDto();

        //act
        categoria.setId(1);

        //assert
        final Field field = categoria.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(1, field.get(categoria), success);
    }

    @Test
    void getNombre_getsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final CategoriaDto categoria = new CategoriaDto();
        final Field field = categoria.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        field.set(categoria, "Matematica");

        //act
        final String result = categoria.getNombre();

        //assert
        assertEquals("Matematica", result, success);
    }

    @Test
    void setNombre_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final CategoriaDto categoria = new CategoriaDto();

        //act
        categoria.setNombre("Matematica");

        //assert
        final Field field = categoria.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        assertEquals("Matematica", field.get(categoria), success);
    }

    @Test
    void getDescripcion_getsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final CategoriaDto categoria = new CategoriaDto();
        final Field field = categoria.getClass().getDeclaredField("descripcion");
        field.setAccessible(true);
        field.set(categoria, "Descripcion Categoria");

        //act
        final String result = categoria.getDescripcion();

        //assert
        assertEquals("Descripcion Categoria", result, success);
    }

    @Test
    void setDescripcion_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final CategoriaDto categoria = new CategoriaDto();
        //act
        categoria.setDescripcion("Descripcion Categoria");

        //assert
        final Field field = categoria.getClass().getDeclaredField("descripcion");
        field.setAccessible(true);
        assertEquals("Descripcion Categoria", field.get(categoria), success);
    }
}