package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.enums.Idioma;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.repositories.LibroRepository;
import com.icunoc.biblioteca.repositories.PrestamoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibrosServiceImplTest {

    Calendar fechaActual = Calendar.getInstance();
    byte[] imagenBytes = "bytes".getBytes();

    @Autowired
    LibroRepository libroRepository = Mockito.mock(LibroRepository.class);

    LibrosServiceImpl libroServiceImpl = new LibrosServiceImpl();

    @BeforeEach
    void setUp() {
        Libro libroMock = new Libro();

        libroMock.setIdLibro(1);
        libroMock.setCodigo("L1");
        libroMock.setNombre("Ecuaciones");
        libroMock.setAutor("Luis Hernandez");
        libroMock.setStock(5);
        libroMock.setEdicion(5);
        libroMock.setFechaPublicacion(fechaActual);
        libroMock.setIdioma(Idioma.ESPAÑOL);
        libroMock.setPathImagen(imagenBytes);

        List<Libro> miListLibroMock = Arrays.asList(libroMock);

        Mockito.when(libroRepository.findByIdLibro(1)).thenReturn(libroMock);
        Mockito.when(libroRepository.findAll()).thenReturn(miListLibroMock);
        Mockito.when(libroRepository.findById(1)).thenReturn(Optional.of(libroMock));
        Mockito.when(libroRepository.findByNombre("Ecuaciones")).thenReturn(Optional.of(libroMock));
        Mockito.when(libroRepository.findByCodigo("L1")).thenReturn(Optional.of(libroMock));
        Mockito.when(libroRepository.existsById(1)).thenReturn(true);
        Mockito.when(libroRepository.existsByNombre("Ecuaciones")).thenReturn(true);
        Mockito.when(libroRepository.existsByCodigo("L1")).thenReturn(true);

    }

    @Test
    void listarId() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        Libro nuevoLibro;
        //Act
        nuevoLibro = libroServiceImpl.listarId(1);
        //Assert
        Assertions.assertEquals(1, nuevoLibro.getIdLibro());
    }

    @Test
    void list() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        List<Libro> nuevaLista;
        //Act
        nuevaLista = libroServiceImpl.list();
        //Assert
        Assertions.assertEquals(1, nuevaLista.size());
    }

    @Test
    void add() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        Libro nuevoLibro = new Libro();
        //Act}
        libroServiceImpl.add(nuevoLibro);
        //Assert
    }

    @Test
    void getOne() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        Optional<Libro> libro;
        //Act
        libro = libroServiceImpl.getOne(1);
        //Assert
        Assertions.assertEquals(1, libro.get().getIdLibro());
    }

    @Test
    void getByNombre() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        Optional<Libro> libro;
        //Act
        libro = libroServiceImpl.getByNombre("Ecuaciones");
        //Assert
        Assertions.assertEquals("Ecuaciones", libro.get().getNombre());
    }

    @Test
    void getByCodigo() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        Optional<Libro> libro;
        //Act
        libro = libroServiceImpl.getByCodigo("L1");
        //Assert
        Assertions.assertEquals("L1", libro.get().getCodigo());
    }

    @Test
    void save() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        //Act
        Libro nuevoLibro = Mockito.mock(Libro.class);
        //Assert
        libroServiceImpl.save(nuevoLibro);
    }

    @Test
    void update() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        //Act
        Libro nuevoLibro = Mockito.mock(Libro.class);
        //Assert
        libroServiceImpl.update(nuevoLibro);
    }

    @Test
    void delete() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        //Act
        libroServiceImpl.delete(1);
        //Assert
    }

    @Test
    void existsById() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        boolean libroExists;
        //Act
        libroExists = libroServiceImpl.existsById(1);
        //Assert
        Assertions.assertEquals(true, libroExists);
    }

    @Test
    void existsByNombre() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        boolean libroExists;
        //Act
        libroExists = libroServiceImpl.existsByNombre("Ecuaciones");
        //Assert
        Assertions.assertEquals(true, libroExists);
    }

    @Test
    void existsByCodigo() {
        //Arrange
        libroServiceImpl.setRepositoryMock(libroRepository);
        boolean libroExists;
        //Act
        libroExists = libroServiceImpl.existsByCodigo("L1");
        //Assert
        Assertions.assertEquals(true, libroExists);
    }
}