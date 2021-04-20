package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.AsignacionLibro;
import com.icunoc.biblioteca.repositories.AsignacionLibroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class AsignacionLibroServiceTest {

    AsignacionLibroRepository repository = Mockito.mock(AsignacionLibroRepository.class);
    List<AsignacionLibro> asignaciones;
    AsignacionLibro asignacion = Mockito.mock(AsignacionLibro.class);
    AsignacionLibroService service = new AsignacionLibroService();
    @BeforeEach
    void setUp() {
        asignaciones = new ArrayList<>();
        Mockito.when(asignacion.getIdAsignacionLibro()).thenReturn(1);
        Mockito.when(asignacion.getIdLibro()).thenReturn(11);
        Mockito.when(asignacion.getIdCategoria()).thenReturn(12);
        asignaciones.add(asignacion);
        service.setRepository(repository);
    }

    @Test
    void list() {
        //arrange
        Mockito.when(repository.findAll()).thenReturn(asignaciones);
        //act
        List<AsignacionLibro> listaAsignaciones = service.list();
        //assert
        Assertions.assertEquals(1,listaAsignaciones.get(0).getIdAsignacionLibro());
        Assertions.assertEquals(11,listaAsignaciones.get(0).getIdLibro());
        Assertions.assertEquals(12,listaAsignaciones.get(0).getIdCategoria());
    }

    @Test
    void listByBook() {
        //arrange
        Mockito.when(repository.findAsignacionLibroByIdLibro(ArgumentMatchers.anyInt())).thenReturn(asignaciones);
        //act
        List<AsignacionLibro> listaAsignaciones = service.listByBook(1);
        //assert
        Assertions.assertEquals(1,listaAsignaciones.get(0).getIdAsignacionLibro());
        Assertions.assertEquals(11,listaAsignaciones.get(0).getIdLibro());
        Assertions.assertEquals(12,listaAsignaciones.get(0).getIdCategoria());
    }

    @Test
    void listByCategory() {
        //arrange
        Mockito.when(repository.findAsignacionLibroByIdCategoria(ArgumentMatchers.anyInt())).thenReturn(asignaciones);
        //act
        List<AsignacionLibro> listaAsignaciones = service.listByCategory(1);
        //assert
        Assertions.assertEquals(1,listaAsignaciones.get(0).getIdAsignacionLibro());
        Assertions.assertEquals(11,listaAsignaciones.get(0).getIdLibro());
        Assertions.assertEquals(12,listaAsignaciones.get(0).getIdCategoria());
    }

    @Test
    void existsByIdIfTrue() {
        //arrange
        Mockito.when(repository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        //act
        boolean exists = service.existsById(1);
        //assert
        Assertions.assertEquals(true,exists);
    }

    @Test
    void existsByIdIfFalse() {
        //arrange
        Mockito.when(repository.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        //act
        boolean exists = service.existsById(1);
        //assert
        Assertions.assertEquals(false,exists);
    }

    @Test
    void find() {
        //arrange
        Mockito.when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(asignacion));
        //act
        AsignacionLibro retornoAsignacion = service.find(1).get();
        //assert
        Assertions.assertEquals(1,asignacion.getIdAsignacionLibro());
        Assertions.assertEquals(11,asignacion.getIdLibro());
        Assertions.assertEquals(12,asignacion.getIdCategoria());
    }

    @Test
    void save() {
        //arrange
        Mockito.when(repository.save(ArgumentMatchers.any(AsignacionLibro.class))).thenReturn(asignacion);
        //act
        AsignacionLibro retornoAsignacion = service.save(asignacion);
        //assert
        Assertions.assertEquals(1,retornoAsignacion.getIdAsignacionLibro());
        Assertions.assertEquals(11,retornoAsignacion.getIdLibro());
        Assertions.assertEquals(12,retornoAsignacion.getIdCategoria());
    }

    @Test
    void update() {
        //arrange
        Mockito.when(repository.save(ArgumentMatchers.any(AsignacionLibro.class))).thenReturn(asignacion);
        //act
        AsignacionLibro retornoAsignacion = service.update(asignacion);
        //assert
        Assertions.assertEquals(1,retornoAsignacion.getIdAsignacionLibro());
        Assertions.assertEquals(11,retornoAsignacion.getIdLibro());
        Assertions.assertEquals(12,retornoAsignacion.getIdCategoria());
    }

    @Test
    void delete() {
        //arrange
        Mockito.lenient().doNothing().when(repository).deleteById(ArgumentMatchers.anyInt());
        //act
        service.delete(1);
        //assert
    }

    @Test
    void deleteBookAssignation() {
        //arrange
        Mockito.lenient().doNothing().when(repository).deleteAsignacionLibroByIdLibro(ArgumentMatchers.anyInt());
        //act
        service.deleteBookAssignation(1);
        //assert

    }
}