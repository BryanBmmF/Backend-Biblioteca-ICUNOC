package com.icunoc.biblioteca.security.services;

import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.security.entities.AuthToken;
import com.icunoc.biblioteca.security.repositories.AuthTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AuthTokenServiceImplTest {

    @Autowired
    AuthTokenRepository repositoryMock = Mockito.mock(AuthTokenRepository.class);

    AuthTokenServiceImpl authTokenService = new AuthTokenServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void update() {
        //Arrage
        authTokenService.setRepositoryMock(repositoryMock);
        AuthToken authToken = Mockito.mock(AuthToken.class);
        //Act
        authTokenService.update(authToken);

        //Assert
    }

    @Test
    void remove() {
        //Arrage
        authTokenService.setRepositoryMock(repositoryMock);
        AuthToken authToken = Mockito.mock(AuthToken.class);
        //Act
        authTokenService.remove(authToken);

        //Assert
    }

    @Test
    void isAuthTokenExpired() {
        //Arrage
        authTokenService.setRepositoryMock(repositoryMock);
        //AuthToken authToken = Mockito.mock(AuthToken.class);

        AuthToken authToken = new AuthToken();
        AuthToken spy = Mockito.spy(authToken);
        User user = Mockito.mock(User.class);
        spy.setUser(user);
        spy.setLastAccessTime(LocalDateTime.now());
        spy.setId("12345678asdfghjkl");

        boolean result;
        //Act
        result = authTokenService.isAuthTokenExpired(spy);
        System.out.println(result);
        //Assert
    }

    @Test
    void createToken() {
        //Arrage
        authTokenService.setRepositoryMock(repositoryMock);
        //AuthToken authToken = Mockito.mock(AuthToken.class);

        AuthToken authToken = new AuthToken();
        AuthToken spy = Mockito.spy(authToken);
        User user = Mockito.mock(User.class);
        spy.setUser(user);
        spy.setLastAccessTime(LocalDateTime.now());
        spy.setId("12345678asdfghjkl");

        AuthToken result;
        //Act
        result = authTokenService.createToken(user);
        System.out.println(result);
        //Assert

    }

    @Test
    void findById() {
        //Arrage
        authTokenService.setRepositoryMock(repositoryMock);
        //Act
        authTokenService.findById("12345678asdfghjkl");

        //Assert
    }

    @Test
    void findByUser() {
        //Arrage
        authTokenService.setRepositoryMock(repositoryMock);
        User user = Mockito.mock(User.class);

        //Act
        authTokenService.findByUser(user);

        //Assert

    }
}