package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.LibroDto;
import com.icunoc.biblioteca.enums.Idioma;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.services.LibrosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibroControllerTest {

    @Autowired
    LibrosService service = Mockito.mock(LibrosService.class);

    @Autowired
    LibroController libroController = new LibroController();

    //libroDto Mock
    @Autowired
    LibroDto libroDto =  Mockito.mock(LibroDto.class);

    @BeforeEach
    void setUp() {
        Calendar today = Calendar.getInstance();
        byte[] imagenBytes = "bytes".getBytes();
        Libro mockLibro =  new Libro();
        mockLibro.setIdLibro(1);
        mockLibro.setAutor("Autor");
        mockLibro.setCodigo("12345");
        mockLibro.setEdicion(1);
        mockLibro.setFechaPublicacion(today);
        mockLibro.setIdioma(Idioma.INGLES);
        mockLibro.setNombre("Nombre");
        mockLibro.setPathImagen(imagenBytes);
        mockLibro.setStock(10);
        mockLibro.setCategoria(1);

        Mockito.when(service.getByNombre("Nombre")).thenReturn(Optional.of(mockLibro));
        Mockito.when(service.existsByNombre("Nombre")).thenReturn(true);
    }

    @Test
    void listarLibro() {
        libroController.setService(service);
        ResponseEntity<List<Libro>> responseServicio;
        responseServicio = libroController.listarLibro();
        System.out.println(responseServicio);
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void getByIdIncorrect(){
        libroController.setService(service);
        ResponseEntity<Libro> responseServicio;
        service.existsById(1);
        responseServicio = libroController.getById(1);
        System.out.println(responseServicio);
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
    @Test
    void getById(){
        libroController.setService(service);
        ResponseEntity<Libro> responseServicio;
        service.existsById(1);
        responseServicio = libroController.getById(1);
        System.out.println(responseServicio);
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void getByNombre(){
        libroController.setService(service);
        ResponseEntity<Libro> responseServicio;
        service.existsByNombre("Nombre");
        responseServicio = libroController.getByNombre("Nombre");
        System.out.println(responseServicio);
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());

    }
    @Test
    void getByNombreIncorrect(){
        libroController.setService(service);
        ResponseEntity<Libro> responseServicio;
        service.existsByNombre("aaaaa");
        responseServicio = libroController.getByNombre("ddd");
        System.out.println(responseServicio);
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());

    }

    @Test
    void create() {
        Calendar today = Calendar.getInstance();
        byte[] imagenBytes = "bytes".getBytes();
        libroController.setService(service);
        ResponseEntity<?> responseServicio;
        LibroDto libroDto1 = new LibroDto();
        libroDto1.setId(1);
        libroDto1.setAutor("Autor1");
        libroDto1.setCodigo("Codigo1");
        libroDto1.setEdicion(1);
        libroDto1.setFechaPublicacion(today);
        libroDto1.setIdioma(Idioma.INGLES);
        libroDto1.setNombre("Pruebatest1");
        libroDto1.setPathImagen(imagenBytes);
        libroDto1.setStock(1);
        libroDto1.setIdCategoria(1);

        responseServicio = libroController.create(libroDto1);
        System.out.println(responseServicio);

        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void updateIncorrecto() {
        Calendar today = Calendar.getInstance();
        byte[] imagenBytes = "bytes".getBytes();
        libroController.setService(service);
        ResponseEntity<?> responseServicio;

        LibroDto libroDto1 = new LibroDto();
        libroDto1.setAutor("Autor12");
        libroDto1.setCodigo("Codigo12");
        libroDto1.setEdicion(2);
        libroDto1.setFechaPublicacion(today);
        libroDto1.setIdioma(Idioma.ESPAÑOL);
        libroDto1.setNombre("Pruebatest12");
        libroDto1.setPathImagen(imagenBytes);
        libroDto1.setStock(12);
        libroDto1.setIdCategoria(12);

        Libro libro = service.getByNombre("Nombre").get();
        libro.setAutor(libroDto1.getAutor());
        libro.setCodigo(libroDto1.getCodigo());
        libro.setEdicion(libroDto1.getEdicion());
        libro.setFechaPublicacion(libroDto1.getFechaPublicacion());
        libro.setIdioma(libroDto1.getIdioma());
        libro.setNombre(libroDto1.getNombre());
        libro.setPathImagen(libroDto1.getPathImagen());
        libro.setStock(libroDto1.getStock());
        libro.setCategoria(libroDto1.getIdCategoria());

        responseServicio = libroController.update(1, libroDto1);
        System.out.println(responseServicio);

        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
    @Test
    void updateCorrecto() {
        Calendar today = Calendar.getInstance();
        byte[] imagenBytes = "bytes".getBytes();
        libroController.setService(service);
        ResponseEntity<?> responseServicio;

        LibroDto libroDto1 = new LibroDto();
        libroDto1.setAutor("Autor12");
        libroDto1.setCodigo("Codigo12");
        libroDto1.setEdicion(2);
        libroDto1.setFechaPublicacion(today);
        libroDto1.setIdioma(Idioma.ESPAÑOL);
        libroDto1.setNombre("Pruebatest12");
        libroDto1.setPathImagen(imagenBytes);
        libroDto1.setStock(12);
        libroDto1.setIdCategoria(12);

        Libro libro = service.getOne(1).get();
        libro.setAutor(libroDto1.getAutor());
        libro.setCodigo(libroDto1.getCodigo());
        libro.setEdicion(libroDto1.getEdicion());
        libro.setFechaPublicacion(libroDto1.getFechaPublicacion());
        libro.setIdioma(libroDto1.getIdioma());
        libro.setNombre(libroDto1.getNombre());
        libro.setPathImagen(libroDto1.getPathImagen());
        libro.setStock(libroDto1.getStock());
        libro.setCategoria(libroDto1.getIdCategoria());

        responseServicio = libroController.update(1, libroDto1);
        System.out.println(responseServicio);

        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void deleteIncorrecto() {
        //Arrage
        libroController.setService(service);
        ResponseEntity<?> responseServicio;

        //Act
        service.existsById(1);
        responseServicio = libroController.delete(1);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
    @Test
    void deleteCorrecto() {
        //Arrage
        libroController.setService(service);
        ResponseEntity<?> responseServicio;

        //Act
        service.existsByNombre("Nombre");
        responseServicio = libroController.delete(1);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
}
