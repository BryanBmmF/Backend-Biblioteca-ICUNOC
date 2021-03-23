package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.dto.UserDto;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class UserControllerTest {
    /*Dependencias*/

    //servicio mock de usuarios
    @Autowired
    UserService userServiceMock = Mockito.mock(UserService.class);

    //userDto Mock
    @Autowired
    UserDto userDto = Mockito.mock(UserDto.class);

    //Obejeto del tipo controller
    @Autowired
    UserController userController = new UserController();

    @BeforeEach
    void setUp() {
        /*Lo que se hara antes de la pruebas*/

        //declaramos una lista de authorities vacia para el user
        List<GrantedAuthority> listAuthorities= new ArrayList<>();
        //declaramos un user por defecto
        User mockUser = new User();
        mockUser.setId((long) 1);
        mockUser.setNombre("Juan");
        mockUser.setNumeroRegistro("200809831");
        mockUser.setUsername("userJuan");
        mockUser.setPassword("password");
        mockUser.setTipo("Administrador");
        mockUser.setAuthorities(listAuthorities);

        //lista de users
        List<User> miListMock = Arrays.asList(mockUser);

        //lo que retona mockito
        //una lista de usuarios
        Mockito.when(userServiceMock.list()).thenReturn(miListMock);
        //un user basado en un id
        Mockito.when(userServiceMock.getOne(1)).thenReturn(Optional.of(mockUser));
        //un user basado en su nombre
        Mockito.when(userServiceMock.getByNombre("Juan")).thenReturn(Optional.of(mockUser));
        //un user basado en su nombre
        Mockito.when(userServiceMock.getByUsername("userJuan")).thenReturn(Optional.of(mockUser));
        //si existe un id
        Mockito.when(userServiceMock.existsById(1)).thenReturn(true);
        //si no existe un user segun su id
        Mockito.when(userServiceMock.existsById(2)).thenReturn(false);
        //si existe un nombre
        Mockito.when(userServiceMock.existsByNombre("Juan")).thenReturn(true);
        //si no existe un user segun su nombre
        Mockito.when(userServiceMock.existsByNombre("Juan No")).thenReturn(false);
        //si existe un username
        Mockito.when(userServiceMock.existsByUsername("userJuan")).thenReturn(true);
        //si no existe un user segun su username
        Mockito.when(userServiceMock.existsByUsername("userJuanNo")).thenReturn(false);

    }

    @Test
    void listar() {
        //Arrage
        //seteamoss el mock generado
        userController.setService(userServiceMock);
        //declaramos el response
        ResponseEntity<List<User>> responseServicio;

        //Act
        responseServicio = userController.listar();
        System.out.println(responseServicio);

        //Asserts
        //respuesta del servidor ok
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
        //pruebas de getters
        Assertions.assertEquals(1, responseServicio.getBody().get(0).getId());
        Assertions.assertEquals("Juan", responseServicio.getBody().get(0).getNombre());
        Assertions.assertEquals("userJuan", responseServicio.getBody().get(0).getUsername());
        Assertions.assertEquals("200809831", responseServicio.getBody().get(0).getNumeroRegistro());
        Assertions.assertEquals("password", responseServicio.getBody().get(0).getPassword());
        Assertions.assertEquals("Administrador", responseServicio.getBody().get(0).getTipo());
        Assertions.assertEquals(0, responseServicio.getBody().get(0).getAuthorities().size());


    }

    @Test
    void getByIdExist() { //escenario 1 exist id
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        //se evalua primero que exista el user
        userServiceMock.existsById(1);
        //se manda el user como respuesta
        responseServicio = userController.getById(1);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
    @Test
    void getByIdNotExist() { //escenario 2 no exist id
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        //si no existe el user
        userServiceMock.existsById(2);
        //se retona como respuesta un error 404 con un mensaje
        responseServicio = userController.getById(2);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }


    @Test
    void getByNombreExist() { //escenario 1 exist nombre
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        userServiceMock.existsByNombre("Juan");
        responseServicio = userController.getByNombre("Juan");
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
    @Test
    void getByNombreNotExist() { //escenario 2 no exist nombre
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        userServiceMock.existsByNombre("Juan No");
        responseServicio = userController.getByNombre("Juan No");
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }

    @Test
    void getByUsernameExist() { //escenario 1 exist username
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        userServiceMock.existsByUsername("userJuan");
        responseServicio = userController.getByUsername("userJuan");
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void getByUsernameNotExist() { //escenario 2 no exist username
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        userServiceMock.existsByUsername("userJuanNo");
        responseServicio = userController.getByUsername("userJuanNo");
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }

    @Test
    void createUserParameterBlanck() { //escenario 1 parametros en blanco en create
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        //userDto.setNombre("    ");//provocamos el fallo de la primera rama cundo un campo esta en blanco
        //el fallo se autoprovoca porque el userDto mock es falso

        responseServicio = userController.create(userDto);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());
    }
    @Test
    void createUserExist() { //escenario 2 existe el user en create
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        UserDto userDto1 = new UserDto();
        userDto1.setUsername("userJuan");//provocamos el fallo de la segunda rama cuando el user ya existe
        userDto1.setNombre("Juan");
        userDto1.setTipo("Administrador");
        userDto1.setNumeroRegistro("200809831");
        userDto1.setPassword("password");
        userServiceMock.existsByUsername(userDto1.getUsername());


        responseServicio = userController.create(userDto1);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());
    }
    @Test
    void createUserSuccess() { //escenario 3 perfect create
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        //creamos un userDto verdadero sin username repetido
        UserDto userDto1 = new UserDto();
        userDto1.setUsername("userJuanSinRepetir");
        userDto1.setNombre("Juan");
        userDto1.setTipo("Administrador");
        userDto1.setNumeroRegistro("200809831");
        userDto1.setPassword("password");

        responseServicio = userController.create(userDto1);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    /*Esta prueba en este escenario especifico aun esta pendiente*/
    @Test
    void updateUserIdExist() { //los mismo escenario de create
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        //provocamo que ya exista el id para que falle y no haga el update
        userServiceMock.existsById(1);


        responseServicio = userController.update(2,userDto);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
    @Test
    void updateUserParameterBlank() {
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        //el fallo se autoprovoca porque el userDto mock es falso
        responseServicio = userController.update(1,userDto);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());
    }
    @Test
    void updateUserExist() {
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        UserDto userDto1 = new UserDto();
        userDto1.setUsername("userJuan");//provocamos el fallo de la segunda rama cuando el user ya existe
        userDto1.setNombre("Juan");
        userDto1.setTipo("Administrador");
        userDto1.setNumeroRegistro("200809831");
        userDto1.setPassword("password");
        //userServiceMock.getByUsername(userDto1.getUsername());
        userServiceMock.existsByUsername(userDto1.getUsername());
        userServiceMock.getByUsername(userDto1.getUsername()).get().getId();


        responseServicio = userController.update(1,userDto1);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
    @Test
    void updateUserSuccess() {
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        //creamos un userDto verdadero sin username repetido
        UserDto userDto1 = new UserDto();
        userDto1.setUsername("userJuanSinRepetir");
        userDto1.setNombre("Juan");
        userDto1.setTipo("Administrador");
        userDto1.setNumeroRegistro("200809831");
        userDto1.setPassword("password");

        //traemos el usuario mock y le seteamos los nuevos valores del dto
        User user = userServiceMock.getOne(1).get();
        user.setNombre(userDto1.getNombre());
        user.setNumeroRegistro(userDto1.getNumeroRegistro());
        user.setUsername(userDto1.getUsername());
        user.setPassword(userDto1.getPassword());
        user.setTipo(userDto1.getTipo());

        responseServicio = userController.update(1, userDto1);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void deleteUserExist() { //escenario 1 delete correcto
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        userServiceMock.existsById(1);
        responseServicio = userController.delete(1);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
    @Test
    void deleteUserNotExist() { //escenario 2 delete incorrecto
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        userServiceMock.existsById(2);
        responseServicio = userController.delete(2);
        System.out.println(responseServicio);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
}