package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.AsignacionLibroDto;
import com.icunoc.biblioteca.models.AsignacionLibro;
import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.services.AsignacionLibroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AsignacionLibroControllerTest {

    AsignacionLibroService service = Mockito.mock(AsignacionLibroService.class);

    AsignacionLibroDto asignacionLibroDto = Mockito.mock(AsignacionLibroDto.class);

    AsignacionLibro asignacionLibro = Mockito.mock(AsignacionLibro.class);

    AsignacionLibroController asignacionLibroController = new AsignacionLibroController();

    List<AsignacionLibro> asignaciones = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Mockito.when(asignacionLibro.getIdAsignacionLibro()).thenReturn(1);
        Mockito.when(asignacionLibro.getIdLibro()).thenReturn(11);
        Mockito.when(asignacionLibro.getIdCategoria()).thenReturn(12);

        Mockito.when(asignacionLibroDto.getId()).thenReturn(2);
        Mockito.when(asignacionLibroDto.getIdLibro()).thenReturn(21);
        Mockito.when(asignacionLibroDto.getIdCategoria()).thenReturn(22);

        asignaciones.add(asignacionLibro);
        asignacionLibroController.setService(service);


        Mockito.when(service.find(1)).thenReturn(Optional.of(asignacionLibro));
        Mockito.when(service.existsById(1)).thenReturn(true);
        Mockito.lenient().doNothing().when(service).update(ArgumentMatchers.any());
        Mockito.lenient().doNothing().when(service).delete(ArgumentMatchers.anyInt());
    }

    @Test
    void listarCategorias() {
        //arrange
        Mockito.when(service.list()).thenReturn(asignaciones);
        //act
        ResponseEntity<List<AsignacionLibro>> response = asignacionLibroController.listarCategorias();
        //assert
        Assertions.assertEquals(1,response.getBody().get(0).getIdAsignacionLibro());
        Assertions.assertEquals(11,response.getBody().get(0).getIdLibro());
        Assertions.assertEquals(12,response.getBody().get(0).getIdCategoria());
    }
    @Test
    void find() {
        //arrange
        //act
        ResponseEntity<AsignacionLibro> response = asignacionLibroController.find(1);
        //assert
        Assertions.assertEquals(1,response.getBody().getIdAsignacionLibro());
        Assertions.assertEquals(11,response.getBody().getIdLibro());
        Assertions.assertEquals(12,response.getBody().getIdCategoria());
    }
    @Test
    void create() {
        //arrange
        Mockito.lenient().doNothing().when(service).save(ArgumentMatchers.any());
        //act
        ResponseEntity<AsignacionLibro> response = asignacionLibroController.create(asignacionLibroDto);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void delete() {
        //arrange
        Mockito.lenient().doNothing().when(service).delete(ArgumentMatchers.anyInt());
        //act
        ResponseEntity<?> response = asignacionLibroController.delete(1);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}