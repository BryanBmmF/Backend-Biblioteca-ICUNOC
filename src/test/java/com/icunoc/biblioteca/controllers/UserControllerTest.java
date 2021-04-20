package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.UserDto;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class UserControllerTest {
    /*Variables statics y Dependencias*/
    private static final String NOMBRE_DEFAULT = "Juan";
    private static final String NOMBRE_DEFAULT_2 = "Juan No";
    private static final String CARNET_DEFAULT = "200809831";
    private static final String USERNAME_DEFAULT = "userJuan";
    private static final String USERNAME_DEFAULT_2 = "userJuanNo";
    private static final String PASSWORD_DEFAULT = "password";
    private static final String TIPO_DEFAULT = "Administrador";
    private static final String CORREO_DEFAULT = "correo";


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
        mockUser.setNombre(NOMBRE_DEFAULT);
        mockUser.setNumeroRegistro(CARNET_DEFAULT);
        mockUser.setUsername(USERNAME_DEFAULT);
        mockUser.setPassword(PASSWORD_DEFAULT);
        mockUser.setTipo(TIPO_DEFAULT);
        mockUser.setCorreo(CORREO_DEFAULT);
        mockUser.setAuthorities(listAuthorities);

        //lista de users
        List<User> miListMock = Arrays.asList(mockUser);

        //lo que retona mockito
        //una lista de usuarios
        Mockito.when(userServiceMock.list()).thenReturn(miListMock);
        //un user basado en un id
        Mockito.when(userServiceMock.getOne(1)).thenReturn(Optional.of(mockUser));
        //un user basado en su nombre
        Mockito.when(userServiceMock.getByNombre(NOMBRE_DEFAULT)).thenReturn(Optional.of(mockUser));
        //un user basado en su nombre
        Mockito.when(userServiceMock.getByUsername(USERNAME_DEFAULT)).thenReturn(Optional.of(mockUser));
        //si existe un id
        Mockito.when(userServiceMock.existsById(1)).thenReturn(true);
        //si no existe un user segun su id
        Mockito.when(userServiceMock.existsById(2)).thenReturn(false);
        //si existe un nombre
        Mockito.when(userServiceMock.existsByNombre(NOMBRE_DEFAULT)).thenReturn(true);
        //si no existe un user segun su nombre
        Mockito.when(userServiceMock.existsByNombre(NOMBRE_DEFAULT_2)).thenReturn(false);
        //si existe un username
        Mockito.when(userServiceMock.existsByUsername(USERNAME_DEFAULT)).thenReturn(true);
        //si no existe un user segun su username
        Mockito.when(userServiceMock.existsByUsername(USERNAME_DEFAULT_2)).thenReturn(false);

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

        //Asserts
        //respuesta del servidor ok
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
        //pruebas de getters
        Assertions.assertEquals(1, responseServicio.getBody().get(0).getId());
        Assertions.assertEquals(NOMBRE_DEFAULT, responseServicio.getBody().get(0).getNombre());
        Assertions.assertEquals(USERNAME_DEFAULT, responseServicio.getBody().get(0).getUsername());
        Assertions.assertEquals(CARNET_DEFAULT, responseServicio.getBody().get(0).getNumeroRegistro());
        Assertions.assertEquals(PASSWORD_DEFAULT, responseServicio.getBody().get(0).getPassword());
        Assertions.assertEquals(TIPO_DEFAULT, responseServicio.getBody().get(0).getTipo());
        Assertions.assertEquals(CORREO_DEFAULT, responseServicio.getBody().get(0).getCorreo());
        Assertions.assertEquals(true, responseServicio.getBody().get(0).isAccountNonExpired());
        Assertions.assertEquals(true, responseServicio.getBody().get(0).isAccountNonLocked());
        Assertions.assertEquals(true, responseServicio.getBody().get(0).isCredentialsNonExpired());
        Assertions.assertEquals(true, responseServicio.getBody().get(0).isEnabled());
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

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }


    @Test
    void getByNombreExist() { //escenario 1 exist nombre
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        userServiceMock.existsByNombre(NOMBRE_DEFAULT);
        responseServicio = userController.getByNombre(NOMBRE_DEFAULT);
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
        userServiceMock.existsByNombre(NOMBRE_DEFAULT_2);
        responseServicio = userController.getByNombre(NOMBRE_DEFAULT_2);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }

    @Test
    void getByUsernameExist() { //escenario 1 exist username
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        userServiceMock.existsByUsername(USERNAME_DEFAULT);
        responseServicio = userController.getByUsername(USERNAME_DEFAULT);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void getByUsernameNotExist() { //escenario 2 no exist username
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<User> responseServicio;

        //Act
        userServiceMock.existsByUsername(USERNAME_DEFAULT_2);
        responseServicio = userController.getByUsername(USERNAME_DEFAULT_2);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }

    @Test
    void createUserParameterBlanck() { //escenario 1 parametros en blanco en create
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        responseServicio = userController.create(userDto);

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
        userDto1.setUsername(USERNAME_DEFAULT);//provocamos el fallo de la segunda rama cuando el user ya existe
        userDto1.setNombre(NOMBRE_DEFAULT);
        userDto1.setTipo(TIPO_DEFAULT);
        userDto1.setCorreo(CORREO_DEFAULT);
        userDto1.setNumeroRegistro(CARNET_DEFAULT);
        userDto1.setPassword(PASSWORD_DEFAULT);
        userServiceMock.existsByUsername(userDto1.getUsername());


        responseServicio = userController.create(userDto1);

        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());
    }
    @Test
    void createUserSuccess() { //escenario 3 perfect create
        //Arrage
        userController.setService(userServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        UserDto userDto1 = new UserDto(NOMBRE_DEFAULT, CARNET_DEFAULT, "userJuanSinRepetir", PASSWORD_DEFAULT, TIPO_DEFAULT, CORREO_DEFAULT );

        responseServicio = userController.create(userDto1);

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
        userDto1.setUsername(USERNAME_DEFAULT);//provocamos el fallo de la segunda rama cuando el user ya existe
        userDto1.setNombre(NOMBRE_DEFAULT);
        userDto1.setTipo(TIPO_DEFAULT);
        userDto1.setNumeroRegistro(CARNET_DEFAULT);
        userDto1.setPassword(PASSWORD_DEFAULT);
        userServiceMock.existsByUsername(userDto1.getUsername());
        userServiceMock.getByUsername(userDto1.getUsername()).get().getId();


        responseServicio = userController.update(1,userDto1);

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

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
}