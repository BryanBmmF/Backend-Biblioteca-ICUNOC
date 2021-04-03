package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.CategoriaDto;
import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.repositories.CategoriaRepository;
import com.icunoc.biblioteca.services.CategoriaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaControllerTest {

    CategoriaService service = Mockito.mock(CategoriaService.class);
    CategoriaDto categoriaDto = Mockito.mock(CategoriaDto.class);

    @Autowired
    CategoriaController categoriaController = new CategoriaController();

    Categoria mockCategoria;
    List<Categoria> mockCategorias;
    @BeforeEach
    void setUp() {
        mockCategoria = new Categoria();
        mockCategoria.setIdCategoria(1);
        mockCategoria.setDescripcion("Area de matimatica");
        mockCategoria.setNombre("Matematica");
        mockCategoria.setLibros(new ArrayList<Libro>());
        mockCategorias = new ArrayList<>();
        mockCategorias.add(mockCategoria);

        //Mock CategoriaDto
        Mockito.when(categoriaDto.getId()).thenReturn(1);
        Mockito.when(categoriaDto.getNombre()).thenReturn("Matematica");
        Mockito.when(categoriaDto.getDescripcion()).thenReturn("Area de Matematica");



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void find() {
        //arrange
        Mockito.when(service.find(1)).thenReturn(Optional.of(mockCategoria));
        Mockito.when(service.existsById(1)).thenReturn(true);
        categoriaController.setService(service);
        //act
        ResponseEntity<Categoria> response = categoriaController.find(1);
        //assert
        Assertions.assertEquals("Matematica",response.getBody().getNombre());

    }

    @Test
    void listarCategorias() {
        //arrange
        Mockito.when(service.list()).thenReturn(mockCategorias);
        categoriaController.setService(service);
        //act
        ResponseEntity<List<Categoria>> response = categoriaController.listarCategorias();
        //assert
        Assertions.assertEquals("Matematica",response.getBody().get(0).getNombre());
        Assertions.assertEquals(1,response.getBody().size());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void create() {
        //arrange
        Mockito.lenient().doNothing().when(service).save(ArgumentMatchers.any());
        categoriaController.setService(service);
        //act
        ResponseEntity<Categoria> response = categoriaController.create(categoriaDto);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void update() {
        //arrange
        Mockito.when(service.find(1)).thenReturn(Optional.of(mockCategoria));
        Mockito.lenient().doNothing().when(service).update(ArgumentMatchers.any());
        Mockito.when(service.existsById(1)).thenReturn(true);
        categoriaController.setService(service);
        //act
        ResponseEntity<?> response = categoriaController.update(1,categoriaDto);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    void delete() {
        //arrange
        Mockito.lenient().doNothing().when(service).delete(ArgumentMatchers.anyInt());
        Mockito.when(service.existsById(1)).thenReturn(true);
        categoriaController.setService(service);
        //act
        ResponseEntity<?> response = categoriaController.delete(1);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}