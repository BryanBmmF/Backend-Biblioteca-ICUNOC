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
        mensajeTest.setMensaje(MENSAJE_DEFAULT);
    }

    @Test
    void setMensaje() {
        //Arrange
        Mensaje mensajeTestPrueba = new Mensaje(MENSAJE_DEFAULT);
        //Act
        mensajeTestPrueba.setMensaje(MENSAJE_DEFAULT);
        //Arrange
        assertTrue(mensajeTestPrueba.getMensaje() == MENSAJE_DEFAULT);
    }
}