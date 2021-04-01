package com.icunoc.biblioteca.models;

import com.icunoc.biblioteca.services.CategoriaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

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
        assertEquals(158, result, "Valor obtenido");
        System.out.println("IDCategoria obtenido con exito");
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
        assertEquals(1, field.get(categoria), "Valores coinciden en set");
        System.out.println("IDCategoria modificado con exito");
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
        assertEquals("Matematica", result, "Valor obtenido");
        System.out.println("Nombre obtenido con exito");
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
        assertEquals("Matematica", field.get(categoria), "Valores coinciden en set");
        System.out.println("Nombre modificado con exito");
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
        assertEquals("Descripcion Categoria", result, "Valor obtenido");
        System.out.println("Descripcion obtenida con exito");
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
        assertEquals("Descripcion Categoria", field.get(categoria), "Valores coinciden en set");
        System.out.println("Descripcion modificada con exito");
    }

    @Test
    void getLibros_getsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Categoria categoria = new Categoria();
        final Field field = categoria.getClass().getDeclaredField("libros");
        field.setAccessible(true);
        field.set(categoria, new ArrayList<Libro>());

        //act
        final List<Libro> result = categoria.getLibros();

        //assert
        assertEquals(0, result.size(), "Valor obtenido");
        System.out.println("Libros obtenidos con exito");
    }

    @Test
    void setLibros_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final Categoria categoria = new Categoria();
        final CategoriaService categoriaService = new CategoriaService();
        //act
        categoria.setLibros(new ArrayList<Libro>());

        //assert
        final Field field = categoria.getClass().getDeclaredField("libros");
        field.setAccessible(true);
        final List<Libro> libro = (List<Libro>)field.get(categoria);
        assertEquals(0, libro.size(), "Valores coinciden en set");
        System.out.println("Libros modificada con exito");
    }
}