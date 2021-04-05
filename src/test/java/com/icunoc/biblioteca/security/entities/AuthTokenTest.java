package com.icunoc.biblioteca.security.entities;

import com.icunoc.biblioteca.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AuthTokenTest {
    @Autowired
    AuthToken authTokenTest = new AuthToken();

    @BeforeEach
    void setUp() {
        /*Lo que se hara antes d ela prueba*/
        authTokenTest.setId("1");
        authTokenTest.setUser(new User());
        authTokenTest.setLastAccessTime(LocalDateTime.now());

    }

    @Test
    void onPrePersist() {
        //Arrange
        AuthToken authTokenPrueba = new AuthToken();
        //LocalDateTime spy = LocalDateTime.now();
        //Act
        //authTokenPrueba.setLastAccessTime(spy);
        authTokenPrueba.onPrePersist();
        //Arrange
    }


    @Test
    void setId() {
        //Arrange
        AuthToken authTokenPrueba = new AuthToken();
        //Act
        authTokenPrueba.setId("1");
        //Arrange
        assertTrue(authTokenPrueba.getId() == "1");
    }

    @Test
    void setLastAccessTime() {
        //Arrange
        AuthToken authTokenPrueba = new AuthToken();
        LocalDateTime spy = LocalDateTime.now();
        //Act
        authTokenPrueba.setLastAccessTime(spy);
        //Arrange
        assertTrue(authTokenPrueba.getLastAccessTime() == spy);
    }

    @Test
    void setUser() {
        //Arrange
        AuthToken authTokenPrueba = new AuthToken();
        User spy = new User();
        //Act
        authTokenPrueba.setUser(spy);
        //Arrange
        assertTrue(authTokenPrueba.getUser() == spy);
    }
}