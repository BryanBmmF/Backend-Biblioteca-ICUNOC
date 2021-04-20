package com.icunoc.biblioteca.security.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

class SecurityControllerTest {
    @Autowired
    Filter springSecurityFilterChain = Mockito.mock(Filter.class);

    SecurityController securityController = new SecurityController();

    @BeforeEach
    void setUp() {
    }

    @Test
    void inPostConstruct() {
        //pendiente
    }

    @Test
    void authorizeAction() {
        //Arrage
        HttpSession httpSession = Mockito.mock(HttpSession.class);

        //Act
        securityController.authorizeAction(httpSession);

        //Assert
    }

    @Test
    void anonAction() {
        //Arrage
        HttpSession httpSession = Mockito.mock(HttpSession.class);

        //Act
        securityController.anonAction();

        //Assert
    }

    @Test
    void loggedAction() {
        //Arrage
        Principal principal = Mockito.mock(Principal.class);

        //Act
        securityController.loggedAction(principal);

        //Assert
    }

    @Test
    void adminAction() {
        //Arrage
        Principal principal = Mockito.mock(Principal.class);

        //Act
        securityController.adminAction(principal);

        //Assert
    }

    @Test
    void userAction() {
        //Arrage
        Principal principal = Mockito.mock(Principal.class);

        //Act
        securityController.userAction(principal);

        //Assert
    }

    @Test
    void accessDeniedHandlerAction() {
        //Arrage
        HttpServletResponse principal = Mockito.mock(HttpServletResponse.class);

        //Act
        securityController.accessDeniedHandlerAction(principal);

        //Assert
    }
}