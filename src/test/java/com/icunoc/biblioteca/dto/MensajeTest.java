package com.icunoc.biblioteca.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MensajeTest {

    @Autowired
    Mensaje mensajeTest = new Mensaje("mensaje");

    @BeforeEach
    void setUp() {
        mensajeTest.setMensaje("mensaje");
    }

    @Test
    void setMensaje() {
        //Arrange
        Mensaje mensajeTestPrueba = new Mensaje("mensaje");
        //Act
        mensajeTestPrueba.setMensaje("mensaje");
        //Arrange
        assertTrue(mensajeTestPrueba.getMensaje() == "mensaje");
    }
}