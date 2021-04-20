package com.icunoc.biblioteca.security.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class BasicAuthenticationEntryPointTest {

    BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();

    @BeforeEach
    void setUp() {
    }

    @Test
    void commence() throws IOException, ServletException {
        //Arrage

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AuthenticationException authenticationException = Mockito.mock(AuthenticationException.class);
        //Act
        try {
            basicAuthenticationEntryPoint.commence(request,response,authenticationException);
        } catch (Exception e){

        }


    }
}