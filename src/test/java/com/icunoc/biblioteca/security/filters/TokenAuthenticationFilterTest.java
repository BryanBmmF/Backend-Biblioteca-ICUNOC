package com.icunoc.biblioteca.security.filters;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.security.services.AuthTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TokenAuthenticationFilterTest {
    @Autowired
    AuthTokenService authTokenServiceMock = Mockito.mock(AuthTokenService.class);

    TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();

    @BeforeEach
    void setUp() {
    }

    @Test
    void doFilter()  {
        //Arrage
        tokenAuthenticationFilter.setAuthTokenServiceMock(authTokenServiceMock);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader(AppConstants.AUTH_TOKEN_NAME)).thenReturn("werwr2343242rwerewr43432");
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        //Act
        try {
            tokenAuthenticationFilter.doFilter(request,response,chain);
        } catch (Exception e){

        }


    }
}