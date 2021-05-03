package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.models.Comentario;
import com.icunoc.biblioteca.repositories.ComentarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ComentarioServiceTest {

    ComentarioRepository comentarioRepository = Mockito.mock(ComentarioRepository.class);
    Comentario comentario = Mockito.mock(Comentario.class);
    List<Comentario> comentarios = new ArrayList<>();
    ComentarioService service = new ComentarioService();
    @BeforeEach
    void setUp() {
        Mockito.when(comentario.getIdComentario()).thenReturn(1);
        Mockito.when(comentario.getComentario()).thenReturn("Test1");
        Mockito.when(comentario.getNombre()).thenReturn("Test1");
        Mockito.when(comentario.getFecha()).thenReturn(Calendar.getInstance());
        Mockito.when(comentario.getPuntuacion()).thenReturn(1);
        Mockito.when(comentario.getIdLibro()).thenReturn("12");
        comentarios.add(comentario);
        service.setComentarioRepository(comentarioRepository);
    }

    @Test
    void listByBook() {
        //arrange
        Mockito.when(comentarioRepository.findByIdLibro(ArgumentMatchers.anyString())).thenReturn(comentarios);
        //act
        List<Comentario> lista = service.listByBook("12");
        Comentario comment = lista.get(0);
        //assert
        Assertions.assertEquals(comment,comentario);
    }

    @Test
    void save() {
        //arrange
        Mockito.when(comentarioRepository.save(ArgumentMatchers.any(Comentario.class))).thenReturn(comentario);
        //act
        Comentario comment = service.save(new Comentario());
        //assert
        Assertions.assertEquals(comment,comentario);
    }


    @Test
    void setComentarioRepository() throws NoSuchFieldException, IllegalAccessException {

        //act
        service.setComentarioRepository(comentarioRepository);

        //assert
        final Field field = service.getClass().getDeclaredField("comentarioRepository");
        field.setAccessible(true);
        assertEquals(comentarioRepository, field.get(service), "Valor obtenido ");

    }
}