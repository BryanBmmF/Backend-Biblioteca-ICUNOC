package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.enums.RoleType;
import com.icunoc.biblioteca.models.Role;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.repositories.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceImplTest {
    @Autowired
    RoleRepository roleRepositoryMock = Mockito.mock(RoleRepository.class);

    RoleServiceImpl roleService = new RoleServiceImpl();

    @BeforeEach
    void setUp() {

        Role roleMock1 = new Role();
        roleMock1.setId((long)1);
        roleMock1.setRole(RoleType.ROLE_ADMIN.toString());

        Role roleMock2 = new Role();
        roleMock1.setId((long)2);
        roleMock1.setRole(RoleType.ROLE_USER.toString());

        //lista de roles
        List<Role> miListMock = Arrays.asList(roleMock1, roleMock2);
        //mocks metods

        Mockito.when(roleRepositoryMock.findAll()).thenReturn(miListMock);
        Mockito.when(roleRepositoryMock.findByRole(RoleType.ROLE_ADMIN.toString())).thenReturn(roleMock1);

    }

    @Test
    void initialize() {
        //Arrage
        roleService.setRoleRepositoryMock(roleRepositoryMock);

        //Act
        roleService.initialize();
    }

    @Test
    void find() {
        //Arrage
        roleService.setRoleRepositoryMock(roleRepositoryMock);
        Role role;
        //Act
        role = roleService.find(RoleType.ROLE_ADMIN);

        //Assert
        Assertions.assertEquals(2, role.getId());
    }

    @Test
    void findAll() {
        //Arrage
        roleService.setRoleRepositoryMock(roleRepositoryMock);
        List<Role> roleList;
        //Act
        roleList = roleService.findAll();

        //Assert
        Assertions.assertEquals(2, roleList.size());
    }
}