package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.AsignacionLibro;
import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.repositories.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaServiceTest {

    CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);
    List<Categoria> categorias;
    Categoria categoria = Mockito.mock(Categoria.class);
    CategoriaService service = new CategoriaService();

    @BeforeEach
    void setUp() {
        categorias = new ArrayList<>();
        Mockito.when(categoria.getIdCategoria()).thenReturn(1);
        Mockito.when(categoria.getNombre()).thenReturn("Test1");
        Mockito.when(categoria.getDescripcion()).thenReturn("DescTest1");
        categorias.add(categoria);
        service.setRepository(repository);
    }

    @Test
    void list() {
        //arrange
        Mockito.when(repository.findAll()).thenReturn(categorias);
        //act
        List<Categoria> listaAsignaciones = service.list();
        //assert
        Assertions.assertEquals(1,listaAsignaciones.get(0).getIdCategoria());
        Assertions.assertEquals("Test1",listaAsignaciones.get(0).getNombre());
        Assertions.assertEquals("DescTest1",listaAsignaciones.get(0).getDescripcion());
    }

    @Test
    void getOne() {
        //arrange
        Mockito.when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(categoria));
        //act
        Categoria categoria = service.getOne(1).get();
        //assert
        Assertions.assertEquals(1,categoria.getIdCategoria());
        Assertions.assertEquals("Test1",categoria.getNombre());
        Assertions.assertEquals("DescTest1",categoria.getDescripcion());
    }

    @Test
    void getByNombre() {
        //arrange
        Mockito.when(repository.findByNombre(ArgumentMatchers.anyString())).thenReturn(Optional.of(categoria));
        //act
        Categoria categoria = service.getByNombre("Test1").get();
        //assert
        Assertions.assertEquals(1,categoria.getIdCategoria());
        Assertions.assertEquals("Test1",categoria.getNombre());
        Assertions.assertEquals("DescTest1",categoria.getDescripcion());
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
        Mockito.when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(categoria));
        //act
        Categoria categoria = service.getOne(1).get();
        //assert
        Assertions.assertEquals(1,categoria.getIdCategoria());
        Assertions.assertEquals("Test1",categoria.getNombre());
        Assertions.assertEquals("DescTest1",categoria.getDescripcion());
    }

    @Test
    void save() {
        //arrange
        Mockito.when(repository.save(ArgumentMatchers.any(Categoria.class))).thenReturn(categoria);
        //act
        Categoria retornoCategoria = service.save(categoria);
        //assert
        Assertions.assertEquals(1,retornoCategoria.getIdCategoria());
        Assertions.assertEquals("Test1",retornoCategoria.getNombre());
        Assertions.assertEquals("DescTest1",retornoCategoria.getDescripcion());
    }

    @Test
    void update() {
        //arrange
        Mockito.when(repository.save(ArgumentMatchers.any(Categoria.class))).thenReturn(categoria);
        //act
        Categoria retornoCategoria = service.update(categoria);
        //assert
        Assertions.assertEquals(1,retornoCategoria.getIdCategoria());
        Assertions.assertEquals("Test1",retornoCategoria.getNombre());
        Assertions.assertEquals("DescTest1",retornoCategoria.getDescripcion());
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
    void existsByNombreIfTrue() {
        //arrange
        Mockito.when(repository.existsByNombre(ArgumentMatchers.anyString())).thenReturn(true);
        //act
        boolean exists = service.existsByNombre("Test1");
        //assert
        Assertions.assertEquals(true,exists);
    }

    @Test
    void existsByNombreIfFalse() {
        //arrange
        Mockito.when(repository.existsByNombre(ArgumentMatchers.anyString())).thenReturn(false);
        //act
        boolean exists = service.existsByNombre("Test2");
        //assert
        Assertions.assertEquals(false,exists);
    }
}