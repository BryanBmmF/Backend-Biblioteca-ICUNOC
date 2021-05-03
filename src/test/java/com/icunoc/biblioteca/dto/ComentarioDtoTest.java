package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.models.Comentario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ComentarioDtoTest {

    private static final String SUCCESS = "Valor obtenido ";

    @BeforeEach
    void setUp() {

    }
    @Test
    void getIdentificacion() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();
        final Field field = comentario.getClass().getDeclaredField("identificacion");
        field.setAccessible(true);
        field.set(comentario, "201731814");

        //act
        final String result = comentario.getIdentificacion();

        //assert
        assertEquals("201731814", result, SUCCESS);
    }

    @Test
    void setIdentificacion() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();

        //act
        comentario.setIdentificacion("201731814");

        //assert
        final Field field = comentario.getClass().getDeclaredField("identificacion");
        field.setAccessible(true);
        assertEquals("201731814", field.get(comentario), SUCCESS);

    }

    @Test
    void getId() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();
        final Field field = comentario.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(comentario, 200);

        //act
        final int result = comentario.getId();

        //assert
        assertEquals(200, result, SUCCESS);
    }

    @Test
    void setId() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();

        //act
        comentario.setId(12);

        //assert
        final Field field = comentario.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(12, field.get(comentario), SUCCESS);

    }

    @Test
    void getNombre() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();
        final Field field = comentario.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        field.set(comentario, "test1");

        //act
        final String result = comentario.getNombre();

        //assert
        assertEquals("test1", result, SUCCESS);
    }

    @Test
    void setNombre() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();

        //act
        comentario.setNombre("TEST1");

        //assert
        final Field field = comentario.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        assertEquals("TEST1", field.get(comentario), SUCCESS);

    }

    @Test
    void getComentario() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();
        final Field field = comentario.getClass().getDeclaredField("comentario");
        field.setAccessible(true);
        field.set(comentario, "test1");

        //act
        final String result = comentario.getComentario();

        //assert
        assertEquals("test1", result, SUCCESS);
    }

    @Test
    void setComentario() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();

        //act
        comentario.setComentario("TEST1");

        //assert
        final Field field = comentario.getClass().getDeclaredField("comentario");
        field.setAccessible(true);
        assertEquals("TEST1", field.get(comentario), SUCCESS);

    }

    @Test
    void getFecha()  throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();
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
        final ComentarioDto comentario = new ComentarioDto();
        final Calendar date = Calendar.getInstance();
        //act
        comentario.setFecha(date);

        //assert
        final Field field = comentario.getClass().getDeclaredField("fecha");
        field.setAccessible(true);
        assertEquals(date, field.get(comentario), SUCCESS);

    }

    @Test
    void getPuntuacion() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();
        final Field field = comentario.getClass().getDeclaredField("puntuacion");
        field.setAccessible(true);
        field.set(comentario, 200);

        //act
        final int result = comentario.getPuntuacion();

        //assert
        assertEquals(200, result, SUCCESS);
    }

    @Test
    void setPuntuacion() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();

        //act
        comentario.setPuntuacion(10);

        //assert
        final Field field = comentario.getClass().getDeclaredField("puntuacion");
        field.setAccessible(true);
        assertEquals(10, field.get(comentario), SUCCESS);

    }

    @Test
    void getIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();
        final Field field = comentario.getClass().getDeclaredField("idLibro");
        field.setAccessible(true);
        field.set(comentario, "2550");

        //act
        final String result = comentario.getIdLibro();

        //assert
        assertEquals("2550", result, SUCCESS);
    }

    @Test
    void setIdLibro() throws NoSuchFieldException, IllegalAccessException {
        //arrange
        final ComentarioDto comentario = new ComentarioDto();

        //act
        comentario.setIdLibro("2550");

        //assert
        final Field field = comentario.getClass().getDeclaredField("idLibro");
        field.setAccessible(true);
        assertEquals("2550", field.get(comentario), SUCCESS);

    }
}