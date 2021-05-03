package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.ComentarioDto;
import com.icunoc.biblioteca.models.Comentario;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.ComentarioService;
import com.icunoc.biblioteca.services.PrestamoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComentarioControllerTest {

    ComentarioService comentarioService = Mockito.mock(ComentarioService.class);
    PrestamoServiceImpl prestamoService = Mockito.mock(PrestamoServiceImpl.class);
    ComentarioDto comentarioDto = Mockito.mock(ComentarioDto.class);
    Comentario comentario = Mockito.mock(Comentario.class);
    Prestamo prestamo = Mockito.mock(Prestamo.class);
    ComentarioController controller = new ComentarioController();
    List<Comentario> comentarios = new ArrayList<>();
    List<Prestamo> prestamos = new ArrayList<>();
    @BeforeEach
    void setUp() {
        comentarios.add(comentario);
        prestamos.add(prestamo);
        controller.injectMocks(comentarioService,prestamoService);
    }

    @Test
    void listByBook() {
        //arrange
        Mockito.when(comentarioService.listByBook(ArgumentMatchers.anyString())).thenReturn(comentarios);
        //act
        ResponseEntity<List<Comentario>> response = controller.listByBook("12");
        //assert
        Assertions.assertEquals(comentario,response.getBody().get(0));
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void create() {
        //arrange
        Mockito.when(prestamoService.findByCarnetOrDPIOfBook(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(prestamos);
        Mockito.when(comentarioDto.getId()).thenReturn(1);
        Mockito.when(comentarioDto.getNombre()).thenReturn("TEST1");
        Mockito.when(comentarioDto.getPuntuacion()).thenReturn(1);
        Mockito.when(comentarioDto.getComentario()).thenReturn("TEST1");
        Mockito.when(comentarioDto.getIdentificacion()).thenReturn("201731814");
        Mockito.when(comentarioDto.getFecha()).thenReturn(Calendar.getInstance());
        Mockito.when(comentarioDto.getIdLibro()).thenReturn("2000");
        Mockito.when(comentarioService.save(ArgumentMatchers.any(Comentario.class))).thenReturn(comentario);
        //act
        ResponseEntity<Comentario> response = controller.create(comentarioDto);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    void createErrorCuzFieldIsBlank() {
        //arrange
        Mockito.when(prestamoService.findByCarnetOrDPIOfBook(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(prestamos);
        Mockito.when(comentarioDto.getId()).thenReturn(1);
        Mockito.when(comentarioDto.getNombre()).thenReturn("");
        Mockito.when(comentarioDto.getPuntuacion()).thenReturn(1);
        Mockito.when(comentarioDto.getComentario()).thenReturn("TEST1");
        Mockito.when(comentarioDto.getIdentificacion()).thenReturn("201731814");
        Mockito.when(comentarioDto.getFecha()).thenReturn(Calendar.getInstance());
        Mockito.when(comentarioDto.getIdLibro()).thenReturn("2000");
        Mockito.when(comentarioService.save(ArgumentMatchers.any(Comentario.class))).thenReturn(comentario);
        //act
        ResponseEntity<Comentario> response = controller.create(comentarioDto);
        //assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

    }

    @Test
    void createErrorCuzUserDoesntHaveAFinalizedReservation() {
        //arrange
        prestamos.clear();
        Mockito.when(prestamoService.findByCarnetOrDPIOfBook(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(prestamos);
        Mockito.when(comentarioDto.getId()).thenReturn(1);
        Mockito.when(comentarioDto.getNombre()).thenReturn("TEST1");
        Mockito.when(comentarioDto.getPuntuacion()).thenReturn(1);
        Mockito.when(comentarioDto.getComentario()).thenReturn("TEST1");
        Mockito.when(comentarioDto.getIdentificacion()).thenReturn("201731814");
        Mockito.when(comentarioDto.getFecha()).thenReturn(Calendar.getInstance());
        Mockito.when(comentarioDto.getIdLibro()).thenReturn("2000");
        Mockito.when(comentarioService.save(ArgumentMatchers.any(Comentario.class))).thenReturn(comentario);
        //act
        ResponseEntity<Comentario> response = controller.create(comentarioDto);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }
}