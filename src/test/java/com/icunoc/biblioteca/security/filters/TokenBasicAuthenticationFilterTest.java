package com.icunoc.biblioteca.security.filters;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.security.entities.AuthToken;
import com.icunoc.biblioteca.security.services.AuthTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

class TokenBasicAuthenticationFilterTest {
    @Autowired
    AuthTokenService authTokenServiceMock = Mockito.mock(AuthTokenService.class);

    @Autowired
    AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);


    TokenBasicAuthenticationFilter tokenBasicAuthenticationFilter = new TokenBasicAuthenticationFilter(authenticationManager);
    @BeforeEach
    void setUp() {
    }

    @Test
    void doFilterInternal() {
        //Arrage
        tokenBasicAuthenticationFilter.setAuthTokenServiceMock(authTokenServiceMock);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);

        //Act
        try {
            tokenBasicAuthenticationFilter.doFilterInternal(request, response, chain);
        } catch (Exception e){

        }

        //Assert
    }

    @Test
    void onSuccessfulAuthentication() {
        //Arrage
        tokenBasicAuthenticationFilter.setAuthTokenServiceMock(authTokenServiceMock);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        HttpSession httpSession = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession(true)).thenReturn(httpSession);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        //FilterChain chain = Mockito.mock(FilterChain.class);
        Authentication authResult = Mockito.mock(Authentication.class);

        //Act
        try {
            tokenBasicAuthenticationFilter.onSuccessfulAuthentication(request, response, authResult);
        } catch (Exception e){

        }

        //Assert
    }
}