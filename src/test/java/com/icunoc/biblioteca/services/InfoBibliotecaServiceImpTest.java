package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.InfoBiblioteca;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.repositories.InfoBibliotecaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InfoBibliotecaServiceImpTest {

    @Autowired
    InfoBibliotecaRepository repositoryMock = Mockito.mock(InfoBibliotecaRepository.class);

    InfoBibliotecaServiceImp infoBibliotecaService = new InfoBibliotecaServiceImp();


    @BeforeEach
    void setUp() {
        //InfoBibliotecaMock
        InfoBiblioteca infoBibliotecaMock = new InfoBiblioteca();
        infoBibliotecaMock.setId((long)1);
        infoBibliotecaMock.setCorreo("correo");
        infoBibliotecaMock.setDireccion("direccion");
        infoBibliotecaMock.setHorario("horario");
        infoBibliotecaMock.setTelefono("telefono");
        infoBibliotecaMock.setCostoDiaMoroso(5);
        infoBibliotecaMock.setDiasHabilesPrestamo(7);
        infoBibliotecaMock.setCostoGeneralPrestamo(5);

        //retornar info con id 1
        Mockito.when(repositoryMock.findById((long)1)).thenReturn(Optional.of(infoBibliotecaMock));
        //existe con id 1
        Mockito.when(repositoryMock.existsById((long)1)).thenReturn(true);
        //no existe con id 2
        Mockito.when(repositoryMock.existsById((long)2)).thenReturn(false);
    }

    @Test
    void getOne() {
        //Arrage
        infoBibliotecaService.setRepositoryMock(repositoryMock);
        Optional<InfoBiblioteca> infoBiblioteca;
        //Act
        infoBiblioteca = infoBibliotecaService.getOne(1);

        //Assert
        Assertions.assertEquals(1, infoBiblioteca.get().getId());
    }

    @Test
    void update() {
        //Arrage
        infoBibliotecaService.setRepositoryMock(repositoryMock);

        //como no es necesario saber el tipo de User mandamos un mock
        InfoBiblioteca infoBiblioteca = Mockito.mock(InfoBiblioteca.class);

        //Act
        infoBibliotecaService.update(infoBiblioteca);
    }

    @Test
    void existsById() {
        //Arrage
        infoBibliotecaService.setRepositoryMock(repositoryMock);
        boolean infoExist;
        //Act
        infoExist = infoBibliotecaService.existsById(1);

        //Assert
        Assertions.assertEquals(true, infoExist);
    }
}