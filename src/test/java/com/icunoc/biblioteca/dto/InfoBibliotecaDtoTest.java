package com.icunoc.biblioteca.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class InfoBibliotecaDtoTest {
    private static final String CORREO_DEFAULT= "correo";
    private static final String TELEFONO_DEFAULT= "telefono";
    private static final String DIRECCION_DEFAULT= "direccion";
    private static final String HORARIO_DEFAULT= "horario";

    @Autowired
    InfoBibliotecaDto infoBibliotecaDtoTest;

    @BeforeEach
    void setUp() {
        infoBibliotecaDtoTest = new InfoBibliotecaDto();
        infoBibliotecaDtoTest.setCorreo(CORREO_DEFAULT);
        infoBibliotecaDtoTest.setTelefono(TELEFONO_DEFAULT);
        infoBibliotecaDtoTest.setDireccion(DIRECCION_DEFAULT);
        infoBibliotecaDtoTest.setHorario(HORARIO_DEFAULT);
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