package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void seed() {
    }

    @Test
    void list() {

    }

    @Test
    void getOne() {
    }

    @Test
    void getByNombre() {
    }

    @Test
    void getByUsername() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }
}