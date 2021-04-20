package com.icunoc.biblioteca.dto;

import com.icunoc.biblioteca.enums.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    private static final String NOMBRE_DEFAULT = "Juan";
    private static final String CARNET_DEFAULT = "201730159";
    private static final String USERNAME_DEFAULT = "userJuan";
    private static final String PASSWORD_DEFAULT = "password";
    private static final String CORREO_DEFAULT = "correo";

    @Autowired
    UserDto userDtoTest = new UserDto();

    @BeforeEach
    void setUp() {
        /*Lo que se hara antes d ela prueba*/
        userDtoTest.setUsername(USERNAME_DEFAULT);
        userDtoTest.setNombre(NOMBRE_DEFAULT);
        userDtoTest.setNumeroRegistro(CARNET_DEFAULT);
        userDtoTest.setTipo(RoleType.ROLE_ADMIN.toString());
        userDtoTest.setPassword(PASSWORD_DEFAULT);
        userDtoTest.setCorreo(CORREO_DEFAULT);

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
        userDtoPrueba.setTipo(RoleType.ROLE_USER.toString());
        userDtoPrueba.setTipo(RoleType.ROLE_ADMIN.toString());
        //Arrange
        assertTrue(userDtoPrueba.getTipo() == RoleType.ROLE_ADMIN.toString());
    }

    @Test
    void setCorreo() {
        //Arrange
        UserDto userDtoPrueba = new UserDto();
        //Act
        userDtoPrueba.setCorreo("correo");
        //Arrange
        assertTrue(userDtoPrueba.getCorreo() == "correo");
    }
}