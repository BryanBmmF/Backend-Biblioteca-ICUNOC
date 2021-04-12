package com.icunoc.biblioteca.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class InfoBibliotecaDtoTest {

    @Autowired
    InfoBibliotecaDto infoBibliotecaDtoTest = new InfoBibliotecaDto();

    @BeforeEach
    void setUp() {
        infoBibliotecaDtoTest.setCorreo("correo");
        infoBibliotecaDtoTest.setTelefono("telefono");
        infoBibliotecaDtoTest.setDireccion("direccion");
        infoBibliotecaDtoTest.setHorario("horario");
        infoBibliotecaDtoTest.setCostoDiaMoroso(5);
        infoBibliotecaDtoTest.setCostoGeneralPrestamo(5);
        infoBibliotecaDtoTest.setDiasHabilesPrestamo(7);
    }

    @Test
    void setCorreo() {
        //Arrange
        InfoBibliotecaDto infoBibliotecaDto = new InfoBibliotecaDto();
        //Act
        infoBibliotecaDto.setCorreo("correo");
        //Arrange
        assertTrue(infoBibliotecaDto.getCorreo() == "correo");

    }

    @Test
    void setDireccion() {
        //Arrange
        InfoBibliotecaDto infoBibliotecaDto = new InfoBibliotecaDto();
        //Act
        infoBibliotecaDto.setDireccion("direccion");
        //Arrange
        assertTrue(infoBibliotecaDto.getDireccion() == "direccion");
    }

    @Test
    void setTelefono() {
        //Arrange
        InfoBibliotecaDto infoBibliotecaDto = new InfoBibliotecaDto();
        //Act
        infoBibliotecaDto.setTelefono("telefono");
        //Arrange
        assertTrue(infoBibliotecaDto.getTelefono() == "telefono");
    }

    @Test
    void setHorario() {
        //Arrange
        InfoBibliotecaDto infoBibliotecaDto = new InfoBibliotecaDto();
        //Act
        infoBibliotecaDto.setHorario("horario");
        //Arrange
        assertTrue(infoBibliotecaDto.getHorario() == "horario");
    }

    @Test
    void setDiasHabilesPrestamo() {
        //Arrange
        InfoBibliotecaDto infoBibliotecaDto = new InfoBibliotecaDto();
        //Act
        infoBibliotecaDto.setDiasHabilesPrestamo(7);
        //Arrange
        assertTrue(infoBibliotecaDto.getDiasHabilesPrestamo() == 7);
    }

    @Test
    void setCostoDiaMoroso() {
        //Arrange
        InfoBibliotecaDto infoBibliotecaDto = new InfoBibliotecaDto();
        //Act
        infoBibliotecaDto.setCostoDiaMoroso(5);
        //Arrange
        assertTrue(infoBibliotecaDto.getCostoDiaMoroso() == 5);
    }

    @Test
    void setCostoGeneralPrestamo() {
        //Arrange
        InfoBibliotecaDto infoBibliotecaDto = new InfoBibliotecaDto();
        //Act
        infoBibliotecaDto.setCostoGeneralPrestamo(5);
        //Arrange
        assertTrue(infoBibliotecaDto.getCostoGeneralPrestamo() == 5);
    }
}