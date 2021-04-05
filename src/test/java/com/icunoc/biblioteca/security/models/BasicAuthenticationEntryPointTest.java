package com.icunoc.biblioteca.security.models;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthenticationEntryPointTest {

    //@Autowired
    //Gson gsonMock = Mockito.mock(Gson.class);

    BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();

    @BeforeEach
    void setUp() {
    }

    @Test
    void commence() throws IOException, ServletException {
        //Arrage
        //basicAuthenticationEntryPoint.setGsonMock(gsonMock);

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