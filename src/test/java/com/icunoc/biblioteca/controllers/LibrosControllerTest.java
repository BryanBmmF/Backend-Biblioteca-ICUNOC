package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.enums.Idioma;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.LibrosService;
import com.icunoc.biblioteca.services.PrestamoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class LibrosControllerTest {

    @Autowired
    LibrosService librosService = Mockito.mock(LibrosService.class);
    @Autowired
    LibrosController librosController = new LibrosController();

    @BeforeEach
    void setUp() {
        byte[] imagenBytes = "bytes".getBytes();
        Calendar fPublicacion = Calendar.getInstance();
        fPublicacion.set(Calendar.DAY_OF_MONTH,9);
        fPublicacion.set(Calendar.MONTH,5);
        fPublicacion.set(Calendar.YEAR,1998);

        Libro libroMock = new Libro();

        libroMock.setIdLibro(1);
        libroMock.setCodigo("L1");
        libroMock.setNombre("Ecuaciones");
        libroMock.setAutor("Luis Hernandez");
        libroMock.setStock(5);
        libroMock.setEdicion(5);
        libroMock.setFechaPublicacion(fPublicacion);
        libroMock.setIdioma(Idioma.ESPAÑOL);
        libroMock.setPathImagen(imagenBytes);
        libroMock.setCategoria(1);

        Mockito.when(librosService.listarId(1)).thenReturn(libroMock);
    }

    @Test
    void listarId() {
        //Arrange
        Libro libroRespuesta = new Libro();
        librosController.setService(librosService);
        //Act
        libroRespuesta = librosController.listarId(1);
        //Assert
        Assertions.assertEquals("Ecuaciones", libroRespuesta.getNombre());
    }
}