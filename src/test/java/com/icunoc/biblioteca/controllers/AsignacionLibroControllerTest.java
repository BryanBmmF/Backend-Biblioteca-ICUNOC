package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.AsignacionLibroDto;
import com.icunoc.biblioteca.models.AsignacionLibro;
import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.services.AsignacionLibroService;
import com.icunoc.biblioteca.services.CategoriaService;
import com.icunoc.biblioteca.services.LibrosServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;

class AsignacionLibroControllerTest {

    AsignacionLibroService service = Mockito.mock(AsignacionLibroService.class);
    CategoriaService categoriaService = Mockito.mock(CategoriaService.class);
    LibrosServiceImpl librosService = Mockito.mock(LibrosServiceImpl.class);

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
        asignacionLibroController.setService(service,categoriaService,librosService);


        Mockito.when(service.find(1)).thenReturn(Optional.of(asignacionLibro));
        Mockito.when(service.existsById(1)).thenReturn(true);
        Mockito.when(service.update(ArgumentMatchers.any(AsignacionLibro.class))).thenReturn(asignacionLibro);
        Mockito.lenient().doNothing().when(service).delete(ArgumentMatchers.anyInt());
    }

    @Test
    void listarCategorias() {
        //arrange
        Mockito.when(service.list()).thenReturn(asignaciones);
        //act
        ResponseEntity<List<AsignacionLibro>> response = asignacionLibroController.list();
        //assert
        Assertions.assertEquals(1,response.getBody().get(0).getIdAsignacionLibro());
        Assertions.assertEquals(11,response.getBody().get(0).getIdLibro());
        Assertions.assertEquals(12,response.getBody().get(0).getIdCategoria());
    }
    @Test
    void findCategoriesOfBook() {
        //arrange
        Categoria categoria = new Categoria(1,"Categoria","Descripcion");
        Mockito.when(service.listByBook(1)).thenReturn(asignaciones);
        Mockito.when(librosService.existsById(1)).thenReturn(true);
        Mockito.when(categoriaService.find(12)).thenReturn(Optional.of(categoria));


        //act
        ResponseEntity<List<Categoria>> response = asignacionLibroController.findCategoriesOfBook(1);

        //assert

        Assertions.assertEquals(1,response.getBody().get(0).getIdCategoria());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    void findCategoriesOfBookError() {
        //arrange
        Mockito.when(librosService.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        //act
        ResponseEntity<List<Categoria>> response = asignacionLibroController.findCategoriesOfBook(1);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void findBooksOfCategory() {
        //arrange
        Libro libro = Mockito.mock(Libro.class);
        Mockito.when(libro.getIdLibro()).thenReturn(1);
        Mockito.when(service.listByCategory(1)).thenReturn(asignaciones);
        Mockito.when(categoriaService.existsById(1)).thenReturn(true);
        Mockito.when(librosService.getOne(11)).thenReturn(Optional.of(libro));


        //act
        ResponseEntity<List<Libro>> response = asignacionLibroController.findBooksOfCategory(1);

        //assert

        Assertions.assertEquals(1,response.getBody().get(0).getIdLibro());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void findBooksOfCategoryError() {
        //arrange
        Mockito.when(categoriaService.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        //act
        ResponseEntity<List<Libro>> response = asignacionLibroController.findBooksOfCategory(1);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
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
    void findError() {
        //arrange
        Mockito.when(service.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        //act
        ResponseEntity<AsignacionLibro> response = asignacionLibroController.find(1);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void create() {
        //arrange
        Mockito.when(service.save(ArgumentMatchers.any(AsignacionLibro.class))).thenReturn(asignacionLibro);
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

    @Test
    void deleteError() {
        //arrange
        Mockito.lenient().doNothing().when(service).delete(ArgumentMatchers.anyInt());
        Mockito.when(service.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        //act
        ResponseEntity<?> response = asignacionLibroController.delete(1);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void deleteAssignations() {
        //arrange
        Mockito.when(librosService.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        Mockito.lenient().doNothing().when(service).deleteBookAssignation(ArgumentMatchers.anyInt());
        //act
        ResponseEntity<?> response = asignacionLibroController.deleteAssignations(1);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void deleteAssignationsError() {
        //arrange
        Mockito.when(librosService.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        Mockito.lenient().doNothing().when(service).deleteBookAssignation(ArgumentMatchers.anyInt());
        //act
        ResponseEntity<?> response = asignacionLibroController.deleteAssignations(1);
        //assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
}