package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    @Autowired
    UserDto userDtoTest = new UserDto();

    @BeforeEach
    void setUp() {
        /*Lo que se hara antes d ela prueba*/
        userDtoTest.setUsername("userJuan");
        userDtoTest.setNombre("Juan");
        userDtoTest.setNumeroRegistro("201730159");
        userDtoTest.setTipo("Administrador");
        userDtoTest.setPassword("password");

    }


    @Test
    void setNombre() {
        //Arrange
        UserDto userDtoPrueba = new UserDto();
        //Act
        userDtoPrueba.setNombre("Juan");
        //Arrange
        assertTrue(userDtoPrueba.getNombre() == "Juan");
    }


    @Test
    void setNumeroRegistro() {
        //Arrange
        UserDto userDtoPrueba = new UserDto();
        //Act
        userDtoPrueba.setNumeroRegistro("201730159");
        //Arrange
        assertTrue(userDtoPrueba.getNumeroRegistro() == "201730159");
    }


    @Test
    void setUsername() {
        //Arrange
        UserDto userDtoPrueba = new UserDto();
        //Act
        userDtoPrueba.setUsername("userJuan");
        //Arrange
        assertTrue(userDtoPrueba.getUsername() == "userJuan");
    }


    @Test
    void setPassword() {
        //Arrange
        UserDto userDtoPrueba = new UserDto();
        //Act
        userDtoPrueba.setPassword("password");
        //Arrange
        assertTrue(userDtoPrueba.getPassword() == "password");
    }


    @Test
    void setTipo() {
        //Arrange
        UserDto userDtoPrueba = new UserDto();
        //Act
        userDtoPrueba.setTipo("Administrador");
        //Arrange
        assertTrue(userDtoPrueba.getTipo() == "Administrador");
    }
}