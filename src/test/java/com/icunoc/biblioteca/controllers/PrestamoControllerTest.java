package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.PrestamoDto;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.PrestamoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

class PrestamoControllerTest {

    @Autowired
    PrestamoService prestamoServiceMock = Mockito.mock(PrestamoService.class);
    @Autowired
    PrestamoController prestamoController = new PrestamoController();

    @BeforeEach
    void setUp() {
        Prestamo prestamoMock = new Prestamo();
        prestamoMock.setId(1);
        prestamoMock.setNombre("Luis");
        prestamoMock.setApellido("Hernandez");
        prestamoMock.setDpi("1234567891234");
        prestamoMock.setCarnet("201631722");
        prestamoMock.setCarrera("SISTEMAS");
        prestamoMock.setFechaReservacion(null);
        prestamoMock.setFechaInicio(null);
        prestamoMock.setFechaFin(null);
        prestamoMock.setCosto(0);
        prestamoMock.setEstado("RESERVADO");
        prestamoMock.setCodigoReservacion("1234ABCD");
        prestamoMock.setMora(true);
        prestamoMock.setDiasMoroso(1);
        prestamoMock.setCodigoLibro("123ABC");

        Mockito.when(prestamoServiceMock.listarCodigoReservacion("K1QQZKFI")).thenReturn(prestamoMock);
    }

    @Test
    void listarReservacion() {
        //Arrange
        Prestamo respuestaServicio = new Prestamo();
        prestamoController.setService(prestamoServiceMock);
        //Act
        respuestaServicio = prestamoController.listarReservacion("K1QQZKFI");
        //Assert
        Assertions.assertEquals(1, respuestaServicio.getId());
        Assertions.assertEquals("Luis", respuestaServicio.getNombre());
        Assertions.assertEquals("Hernandez", respuestaServicio.getApellido());
        Assertions.assertEquals("1234567891234", respuestaServicio.getDpi());
        Assertions.assertEquals("201631722", respuestaServicio.getCarnet());
        Assertions.assertEquals("SISTEMAS", respuestaServicio.getCarrera());
        Assertions.assertEquals(null, respuestaServicio.getFechaReservacion());
        Assertions.assertEquals(null, respuestaServicio.getFechaInicio());
        Assertions.assertEquals(null, respuestaServicio.getFechaFin());
        Assertions.assertEquals(0, respuestaServicio.getCosto());
        Assertions.assertEquals("RESERVADO", respuestaServicio.getEstado());
        Assertions.assertEquals("1234ABCD", respuestaServicio.getCodigoReservacion());
        Assertions.assertEquals(true, respuestaServicio.isMora());
        Assertions.assertEquals(1, respuestaServicio.getDiasMoroso());
        Assertions.assertEquals("123ABC", respuestaServicio.getCodigoLibro());
    }

    @Test
    void create() {
        ResponseEntity<?> respuesta;
        prestamoController.setService(prestamoServiceMock);
        //respuesta = prestamoController.create()
        PrestamoDto prestamoMockDto = new PrestamoDto();
        prestamoMockDto.setId(1);
        prestamoMockDto.setNombre("Luis");
        prestamoMockDto.setApellido("Hernandez");
        prestamoMockDto.setDpi("1234567891234");
        prestamoMockDto.setCarnet("201631722");
        prestamoMockDto.setCarrera("SISTEMAS");
        prestamoMockDto.setFechaReservacion(null);
        prestamoMockDto.setFechaInicio(null);
        prestamoMockDto.setFechaFin(null);
        prestamoMockDto.setCosto(0);
        prestamoMockDto.setEstado("RESERVADO");
        prestamoMockDto.setCodigoReservacion("1234ABCD");
        prestamoMockDto.setMora(true);
        prestamoMockDto.setDiasMoroso(1);
        prestamoMockDto.setCodigoLibro("123ABC");
        respuesta = prestamoController.create(prestamoMockDto);
        Assertions.assertEquals(200,respuesta.getStatusCodeValue());
    }

    //Test para la busqueda general por estado
    @Test
    void listar(){
        prestamoController.setService(prestamoServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarPrestamos("ACTIVO");
        System.out.println(responseServicio);
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

       //Test para la busqueda x carnet
    @Test
    void listarReservacionxCarnet(){
        prestamoController.setService(prestamoServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarReservacionxCarnet("11111111");
        System.out.println(responseServicio);
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
    //Test para la busqueda x DPI
    @Test
    void listarReservacionxDPI(){
        prestamoController.setService(prestamoServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarReservacionxDPI("1234567891234");
        System.out.println(responseServicio);
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
    //Test para la busqueda x fechaInicio
    @Test
    void listarReservacionxFechaInicio(){
        prestamoController.setService(prestamoServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarReservacionxDPI("2021-04-05");
        System.out.println(responseServicio);
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
    @Test
    void delete(){
        //Arrage
        prestamoController.setService(prestamoServiceMock);
        ResponseEntity<?> responseServicio;
        //Act
        prestamoServiceMock.delete(1);
        responseServicio = prestamoController.delete(1);
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    //Test para iniciar un prestamo
    @Test
    void iniciarPrestamo(){
        //Arrage
        prestamoController.setService(prestamoServiceMock);
        ResponseEntity<?> responseServicio;
        //Act
        prestamoServiceMock.getOne("1234ABCD");
        responseServicio = prestamoController.iniciarPrestamo("1234ABCD");
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
}
