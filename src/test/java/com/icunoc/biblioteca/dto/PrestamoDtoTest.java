package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.constants.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class PrestamoDtoTest {

    PrestamoDto testDePrestamo = new PrestamoDto();
    Calendar fechaPrueba = Calendar.getInstance();
    private static final String DPI_TEST = "1234567891234";
    private static final String CARNET_TEST = "201631722";
    private static final String CARRERA_TEST = "SISTEMAS";
    private static final String CODIGO_RESERVACION_TEST = "1234ABCD";
    private static final String CODIGO_LIBRO_TEST = "123ABC";

    @BeforeEach
    void setUp() {

        fechaPrueba.set(Calendar.DAY_OF_MONTH,9);
        fechaPrueba.set(Calendar.MONTH,5);
        fechaPrueba.set(Calendar.YEAR,1998);

        testDePrestamo.setId(1);
        testDePrestamo.setNombre("Luis");
        testDePrestamo.setApellido("Hernandez");
        testDePrestamo.setDpi(DPI_TEST);
        testDePrestamo.setCarnet(CARNET_TEST);
        testDePrestamo.setCarrera(CARRERA_TEST);
        testDePrestamo.setFechaReservacion(fechaPrueba);
        testDePrestamo.setFechaInicio(fechaPrueba);
        testDePrestamo.setFechaFin(fechaPrueba);
        testDePrestamo.setCosto(0);
        testDePrestamo.setEstado(AppConstants.ESTADO_RESERVADO);
        testDePrestamo.setCodigoReservacion(CODIGO_RESERVACION_TEST);
        testDePrestamo.setCodigoLibro(CODIGO_LIBRO_TEST);
    }

    @Test
    void constructor() {
        //act
            PrestamoDto prestamoDto = new PrestamoDto("Nombre", "Apellido", DPI_TEST, "carnet", "carrera", Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(), 2.2, "Estado", "codigoR", "codLibro");
        assertTrue(prestamoDto.getDpi() == DPI_TEST);
    }

    @Test
    void setCarrera() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCarrera(CARRERA_TEST);
        //Arrange
        assertTrue(prestamoDtoPrueba.getCarrera() == CARRERA_TEST);
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
        prestamoDtoPrueba.setDpi(DPI_TEST);
        //Arrange
        assertTrue(prestamoDtoPrueba.getDpi() == DPI_TEST);
    }


    @Test
    void setCarnet() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCarnet(CARNET_TEST);
        //Arrange
        assertTrue(prestamoDtoPrueba.getCarnet() == CARNET_TEST);
    }

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
        prestamoDtoPrueba.setEstado(AppConstants.ESTADO_RESERVADO);
        //Arrange
        assertTrue(prestamoDtoPrueba.getEstado() == AppConstants.ESTADO_RESERVADO);
    }


    @Test
    void setCodigoReservacion() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCodigoReservacion(CODIGO_RESERVACION_TEST);
        //Arrange
        assertTrue(prestamoDtoPrueba.getCodigoReservacion() == CODIGO_RESERVACION_TEST);
    }

    @Test
    void setCodigoLibro() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setCodigoLibro(CODIGO_LIBRO_TEST);
        //Arrange
        assertTrue(prestamoDtoPrueba.getCodigoLibro() == CODIGO_LIBRO_TEST);
    }

    @Test
    void setMora() {
        //Arrange
        PrestamoDto prestamoDtoPrueba = new PrestamoDto();
        //Act
        prestamoDtoPrueba.setMora(true);
        //Arrange
        assertTrue(prestamoDtoPrueba.isMora());
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
