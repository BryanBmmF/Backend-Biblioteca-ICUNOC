package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.PrestamoDto;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.PrestamoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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
        Assertions.assertEquals(null, respuestaServicio.getFechaReservacion());
        Assertions.assertEquals(null, respuestaServicio.getFechaInicio());
        Assertions.assertEquals(null, respuestaServicio.getFechaFin());
        Assertions.assertEquals(0, respuestaServicio.getCosto());
        Assertions.assertEquals("RESERVADO", respuestaServicio.getEstado());
        Assertions.assertEquals("1234ABCD", respuestaServicio.getCodigoReservacion());
        Assertions.assertEquals("123ABC", respuestaServicio.getCodigoLibro());
        Assertions.assertEquals("SISTEMAS", respuestaServicio.getCarrera());
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
        prestamoMockDto.setCodigoLibro("123ABC");

        respuesta = prestamoController.create(prestamoMockDto);

        Assertions.assertEquals(200,respuesta.getStatusCodeValue());
    }
}