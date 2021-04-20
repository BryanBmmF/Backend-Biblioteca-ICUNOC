package com.icunoc.biblioteca.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    private static final String ROLE_USER= "ROLE_USER";
    @Autowired
    Role roleTest;

    @BeforeEach
    void setUp() {
        roleTest = new Role();
        roleTest.setId((long)1);
        roleTest.setRole(ROLE_USER);
    }

    @Test
    void setId() {
        //Arrange
        Role rolePrueba = new Role();
        //Act
        rolePrueba.setId((long)1);
        //Arrange
        assertTrue(rolePrueba.getId() == 1);
    }

    @Test
    void setRole() {
        //Arrange
        Role rolePrueba = new Role();
        //Act
        rolePrueba.setRole(ROLE_USER);
        //Arrange
        assertTrue(rolePrueba.getRole() == ROLE_USER);
        assertTrue(rolePrueba.getAuthority() == ROLE_USER);
    }
}