package com.icunoc.biblioteca.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MensajeTest {
    private static final String MENSAJE_DEFAULT= "mensaje";

    @Autowired
    Mensaje mensajeTest;

    @BeforeEach
    void setUp() {
        mensajeTest = new Mensaje(MENSAJE_DEFAULT);
        mensajeTest.setInfo(MENSAJE_DEFAULT);
    }

    @Test
    void setMensaje() {
        //Arrange
        Mensaje mensajeTestPrueba = new Mensaje(MENSAJE_DEFAULT);
        //Act
        mensajeTestPrueba.setInfo(MENSAJE_DEFAULT);
        //Arrange
        assertTrue(mensajeTestPrueba.getInfo() == MENSAJE_DEFAULT);
    }
}