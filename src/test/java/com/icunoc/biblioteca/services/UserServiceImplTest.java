package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.enums.RoleType;
import com.icunoc.biblioteca.models.Role;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    @Autowired
    UserRepository repositoryMock = Mockito.mock(UserRepository.class);
    @Autowired
    RoleService roleServiceMock= Mockito.mock(RoleService.class);
    @Autowired
    BCryptPasswordEncoder encoderMock = Mockito.mock(BCryptPasswordEncoder.class);

    UserServiceImpl userService = new UserServiceImpl();

    @BeforeEach
    void setUp() {

        //declaramos una lista de authorities vacia para el user
        List<GrantedAuthority> listAuthorities= new ArrayList<>();
        User mockUser = new User();
        mockUser.setId((long) 1);
        mockUser.setNombre("Juan");
        mockUser.setNumeroRegistro("200809831");
        mockUser.setUsername("userJuan");
        mockUser.setPassword("password");
        mockUser.setTipo("Administrador");
        mockUser.setCorreo("correo");
        mockUser.setAuthorities(listAuthorities);

        //lista de users
        List<User> miListMock = Arrays.asList(mockUser);

        //mocks
        Mockito.when(repositoryMock.findAll()).thenReturn(miListMock);
        //un user basado en un id
        Mockito.when(repositoryMock.findById((long)1)).thenReturn(Optional.of(mockUser));
        //un user basado en su nombre
        Mockito.when(repositoryMock.findByNombre("Juan")).thenReturn(Optional.of(mockUser));
        //un user basado en su username
        Mockito.when(repositoryMock.findByUsername("userJuan")).thenReturn(Optional.of(mockUser));
        Mockito.when(repositoryMock.findUserByUsername("userJuan")).thenReturn(mockUser);
        //si existe un id
        Mockito.when(repositoryMock.existsById((long)1)).thenReturn(true);
        //si no existe un user segun su id
        Mockito.when(repositoryMock.existsById((long)2)).thenReturn(false);
        //si existe un nombre
        Mockito.when(repositoryMock.existsByNombre("Juan")).thenReturn(true);
        //si no existe un user segun su nombre
        Mockito.when(repositoryMock.existsByNombre("Juan No")).thenReturn(false);
        //si existe un username
        Mockito.when(repositoryMock.existsByUsername("userJuan")).thenReturn(true);
        //si no existe un user segun su username
        Mockito.when(repositoryMock.existsByUsername("userJuanNo")).thenReturn(false);

        Mockito.when(encoderMock.encode("password")).thenReturn("passwordEncriptado");

        Role role = new Role();
        role.setId((long)1);
        role.setRole(RoleType.ROLE_ADMIN.toString());
        Mockito.when(roleServiceMock.find(RoleType.ROLE_ADMIN)).thenReturn(role);

        Role role2 = new Role();
        role2.setId((long)2);
        role.setRole(RoleType.ROLE_USER.toString());
        Mockito.when(roleServiceMock.find(RoleType.ROLE_USER)).thenReturn(role2);
    }

    @Test
    void seed() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        userService.setEncoderMock(encoderMock);
        userService.setRoleServiceMock(roleServiceMock);

        //Act
        userService.seed();

        //Assert

    }

    @Test
    void list() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        List<User> list;
        //Act
        list = userService.list();

        //Assert
        Assertions.assertEquals(1, list.size());

    }

    @Test
    void getOne() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        Optional<User> user;
        //Act
        user = userService.getOne(1);

        //Assert
        Assertions.assertEquals(1, user.get().getId());
    }

    @Test
    void getByNombre() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        Optional<User> user;
        //Act
        user = userService.getByNombre("Juan");

        //Assert
        Assertions.assertEquals("Juan", user.get().getNombre());
    }

    @Test
    void getByUsername() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        Optional<User> user;
        //Act
        user = userService.getByUsername("userJuan");

        //Assert
        Assertions.assertEquals("userJuan", user.get().getUsername());
    }

    @Test
    void saveAdmin() { //Escenario 1: Un user administrador
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        userService.setEncoderMock(encoderMock);
        userService.setRoleServiceMock(roleServiceMock);
        Role adminRole = this.roleServiceMock.find(RoleType.ROLE_ADMIN);
        Role userRole = this.roleServiceMock.find(RoleType.ROLE_USER);
        User admin = new User();
        admin.setTipo("Administrador");
        admin.setAuthorities(List.of(adminRole, userRole));

        //Act
        userService.save(admin);


    }
    @Test
    void saveUser() { //Escenario 2: Un user no administrador
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        userService.setEncoderMock(encoderMock);
        userService.setRoleServiceMock(roleServiceMock);

        Role adminRole = this.roleServiceMock.find(RoleType.ROLE_ADMIN);
        Role userRole = this.roleServiceMock.find(RoleType.ROLE_USER);
        User admin = new User();
        admin.setTipo("Bibliotecario");
        admin.setAuthorities(List.of(adminRole, userRole));

        //Act
        userService.save(admin);


    }

    @Test
    void update() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        userService.setEncoderMock(encoderMock);
        userService.setRoleServiceMock(roleServiceMock);

        //como no es necesario saber el tipo de User mandamos un mock
        User admin = Mockito.mock(User.class);

        //Act
        userService.update(admin);
    }

    @Test
    void delete() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        //Act
        userService.delete(1);

    }

    @Test
    void existsById() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        boolean userExist;
        //Act
        userExist = userService.existsById(1);

        //Assert
        Assertions.assertEquals(true, userExist);
    }

    @Test
    void existsByNombre() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        boolean userExist;
        //Act
        userExist = userService.existsByNombre("Juan");

        //Assert
        Assertions.assertEquals(true, userExist);
    }

    @Test
    void existsByUsername() {
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        boolean userExist;
        //Act
        userExist = userService.existsByUsername("userJuan");

        //Assert
        Assertions.assertEquals(true, userExist);
    }

    @Test
    void loadUserByUsernameExist() { //Escenario 1: no es nulo
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        UserDetails userDetails;
        //Act
        repositoryMock.findUserByUsername("userJuan");
        userDetails = userService.loadUserByUsername("userJuan");
        //Assert
       Assertions.assertNotNull(userDetails);

    }

    @Test
    void loadUserByUsernameNotExist() throws UsernameNotFoundException { //Escenario 2: es nulo y lanza la ecepcion
        //Arrage
        userService.setRepositoryMock(repositoryMock);
        UserDetails userDetails;
        //Act
        try {
            repositoryMock.findUserByUsername("userJuan2");
            userDetails = userService.loadUserByUsername("userJuan2");
        }catch (UsernameNotFoundException e){

        }


    }
}