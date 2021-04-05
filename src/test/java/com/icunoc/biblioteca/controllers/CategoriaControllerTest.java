package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.CategoriaDto;
import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.services.CategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        mockCategorias = new ArrayList<>();
        mockCategorias.add(mockCategoria);

        //Mock CategoriaDto
        Mockito.when(categoriaDto.getId()).thenReturn(1);
        Mockito.when(categoriaDto.getNombre()).thenReturn("Matematica");
        Mockito.when(categoriaDto.getDescripcion()).thenReturn("Area de Matematica");



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
    void notFound() {
        //arrange
        Mockito.when(service.find(1)).thenReturn(Optional.of(mockCategoria));
        Mockito.when(service.existsById(1)).thenReturn(false);
        categoriaController.setService(service);
        //act
        ResponseEntity<Categoria> response = categoriaController.find(1);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

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
    void createBlankName() {
        //arrange
        Mockito.lenient().doNothing().when(service).save(ArgumentMatchers.any());
        categoriaController.setService(service);
        Mockito.when(categoriaDto.getNombre()).thenReturn("");
        //act
        ResponseEntity<Categoria> response = categoriaController.create(categoriaDto);
        //assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    void createAlreadyExists() {
        //arrange
        Mockito.lenient().doNothing().when(service).save(ArgumentMatchers.any());
        Mockito.when(service.existsByNombre(ArgumentMatchers.anyString())).thenReturn(false);
        categoriaController.setService(service);
        //act
        ResponseEntity<Categoria> response = categoriaController.create(categoriaDto);
        //assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
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
    void updateExistByIDError() {
        //arrange
        Mockito.when(service.find(1)).thenReturn(Optional.of(mockCategoria));
        Mockito.lenient().doNothing().when(service).update(ArgumentMatchers.any());
        Mockito.when(service.existsById(1)).thenReturn(false);
        categoriaController.setService(service);
        //act
        ResponseEntity<?> response = categoriaController.update(1,categoriaDto);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }

    @Test
    void updateNameIsBlankError() {
        //arrange
        Mockito.when(service.find(1)).thenReturn(Optional.of(mockCategoria));
        Mockito.lenient().doNothing().when(service).update(ArgumentMatchers.any());
        Mockito.when(service.existsById(1)).thenReturn(true);
        Mockito.when(categoriaDto.getNombre()).thenReturn("");
        Mockito.when(categoriaDto.getDescripcion()).thenReturn("");
        categoriaController.setService(service);
        //act
        ResponseEntity<?> response = categoriaController.update(1,categoriaDto);
        //assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

    }

    @Test
    void updateNameAlreadyExistsError() {
        //arrange
        Mockito.when(service.find(1)).thenReturn(Optional.of(mockCategoria));
        Mockito.lenient().doNothing().when(service).update(ArgumentMatchers.any());
        Mockito.when(service.existsById(1)).thenReturn(true);
        Mockito.when(service.existsByNombre(ArgumentMatchers.anyString())).thenReturn(true);
        categoriaController.setService(service);
        //act
        ResponseEntity<?> response = categoriaController.update(1,categoriaDto);
        //assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

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

    @Test
    void deleteIDNotExists() {
        //arrange
        Mockito.lenient().doNothing().when(service).delete(ArgumentMatchers.anyInt());
        Mockito.when(service.existsById(1)).thenReturn(false);
        categoriaController.setService(service);
        //act
        ResponseEntity<?> response = categoriaController.delete(1);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
}