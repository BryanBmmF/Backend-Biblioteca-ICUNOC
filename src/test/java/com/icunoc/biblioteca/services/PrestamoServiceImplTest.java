package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.repositories.PrestamoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoServiceImplTest {

    @Autowired
    PrestamoRepository prestamoRepository = Mockito.mock(PrestamoRepository.class);

    PrestamoServiceImpl prestamoServiceImpl = new PrestamoServiceImpl();

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

        Mockito.when(prestamoRepository.findByCodigoReservacion("1234ABCD")).thenReturn(prestamoMock);
    }

    @Test
    void listarCodigoReservacion() {
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        //Act
        Prestamo prestamoRecivido = prestamoServiceImpl.listarCodigoReservacion("1234ABCD");
        //Assert
        Assertions.assertEquals("1234ABCD",prestamoRecivido.getCodigoReservacion());
    }

    @Test
    void save() {

    }
}