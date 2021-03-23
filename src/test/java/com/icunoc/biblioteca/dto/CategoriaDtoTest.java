package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.services.CategoriaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaDtoTest {

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
        assertEquals(158, result, "Valor obtenido");
        System.out.println("ID obtenido con exito");
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
        assertEquals(1, field.get(categoria), "Valores coinciden en set");
        System.out.println("ID modificado con exito");
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
        assertEquals("Matematica", result, "Valor obtenido");
        System.out.println("Nombre obtenido con exito");
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
        assertEquals("Matematica", field.get(categoria), "Valores coinciden en set");
        System.out.println("Nombre modificado con exito");
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
        assertEquals("Descripcion Categoria", result, "Valor obtenido");
        System.out.println("Descripcion obtenida con exito");
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
        assertEquals("Descripcion Categoria", field.get(categoria), "Valores coinciden en set");
        System.out.println("Descripcion modificada con exito");
    }
}