package com.icunoc.biblioteca.security.controllers;

import com.icunoc.biblioteca.constants.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

class SecurityControllerTest {
    @Autowired
    Filter springSecurityFilterChain = Mockito.mock(Filter.class);

    SecurityController securityController = new SecurityController();

    Map<String, Object> MY_MAP = new HashMap<String, Object>();

    @BeforeEach
    void setUp() {
        MY_MAP.put(AppConstants.AUTH_TOKEN_NAME, AppConstants.AUTH_TOKEN_NAME);
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