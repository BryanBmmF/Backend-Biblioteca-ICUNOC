package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.models.Prestamo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.core.parameters.P;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoDtoTest {


    PrestamoDto prestamoDtoTest = new PrestamoDto();
    Calendar fechaPrueba = Calendar.getInstance();

    @BeforeEach
    void setUp() {

        fechaPrueba.set(Calendar.DAY_OF_MONTH,9);
        fechaPrueba.set(Calendar.MONTH,5);
        fechaPrueba.set(Calendar.YEAR,1998);

        prestamoDtoTest.setId(1);
        prestamoDtoTest.setNombre("Luis");
        prestamoDtoTest.setApellido("Hernandez");
        prestamoDtoTest.setDpi("1234567891234");
        prestamoDtoTest.setCarnet("201631722");
        prestamoDtoTest.setCarrera("SISTEMAS");
        prestamoDtoTest.setFechaReservacion(fechaPrueba);
        prestamoDtoTest.setFechaInicio(fechaPrueba);
        prestamoDtoTest.setFechaFin(fechaPrueba);
        prestamoDtoTest.setCosto(0);
        prestamoDtoTest.setEstado("RESERVADO");
        prestamoDtoTest.setCodigoReservacion("1234ABCD");
        prestamoDtoTest.setCodigoLibro("123ABC");
    }


    @Test
    void setCarrera() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCarrera("SISTEMAS");
        //Arrange
        assertTrue(prestamoDtoPrueba.getCarrera() == "SISTEMAS");
    }


    @Test
    void setId() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setId(1);
        //Arrange
        assertTrue(prestamoDtoPrueba.getId() == 1);
    }


    @Test
    void setNombre() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setNombre("Luis");
        //Arrange
        assertTrue(prestamoDtoPrueba.getNombre() == "Luis");
    }


    @Test
    void setApellido() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setApellido("Rivera");
        //Arrange
        assertTrue(prestamoDtoPrueba.getApellido() == "Rivera");
    }


    @Test
    void setDpi() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setDpi("1234567891234");
        //Arrange
        assertTrue(prestamoDtoPrueba.getDpi() == "1234567891234");
    }


    @Test
    void setCarnet() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCarnet("201631722");
        //Arrange
        assertTrue(prestamoDtoPrueba.getCarnet() == "201631722");
    }

   /* @Test
    void getFechaReservacion() {

    }*/

    @Test
    void setFechaReservacion() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setFechaReservacion(fechaPrueba);
        //Arrange
        assertTrue(prestamoDtoPrueba.getFechaReservacion() == fechaPrueba);
    }


    @Test
    void setFechaInicio() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setFechaInicio(fechaPrueba);
        //Arrange
        assertTrue(prestamoDtoPrueba.getFechaInicio() == fechaPrueba);
    }


    @Test
    void setFechaFin() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setFechaFin(fechaPrueba);
        //Arrange
        assertTrue(prestamoDtoPrueba.getFechaFin() == fechaPrueba);
    }


    @Test
    void setCosto() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCosto(100.1);
        //Arrange
        assertTrue(prestamoDtoPrueba.getCosto() == 100.1);
    }


    @Test
    void setEstado() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setEstado("RESERVADO");
        //Arrange
        assertTrue(prestamoDtoPrueba.getEstado() == "RESERVADO");
    }


    @Test
    void setCodigoReservacion() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCodigoReservacion("1234ABCD");
        //Arrange
        assertTrue(prestamoDtoPrueba.getCodigoReservacion() == "1234ABCD");
    }

    @Test
    void setCodigoLibro() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCodigoLibro("123ABC");
        //Arrange
        assertTrue(prestamoDtoPrueba.getCodigoLibro() == "123ABC");
    }

    @Test
    void setMora() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setMora(true);
        //Arrange
        assertTrue(prestamoDtoPrueba.isMora() == true);
    }

    @Test
    void getDiasMorosos() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setDiasMoroso(11);
        //Arrange
        assertTrue(prestamoDtoPrueba.getDiasMoroso() == 11);
    }
}
