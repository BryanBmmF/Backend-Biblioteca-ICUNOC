package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.InfoBibliotecaDto;
import com.icunoc.biblioteca.dto.UserDto;
import com.icunoc.biblioteca.models.InfoBiblioteca;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.services.InfoBibliotecaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InfoBibliotecaControllerTest {

    //mock service de infoBiblioteca
    @Autowired
    InfoBibliotecaService infoBibliotecaServiceMock = Mockito.mock(InfoBibliotecaService.class);

    //mock de infoBiblitecaDto
    InfoBibliotecaDto infoBibliotecaDto = Mockito.mock(InfoBibliotecaDto.class);

    //contrller
    @Autowired
    InfoBibliotecaController infoBibliotecaController = new InfoBibliotecaController();

    @BeforeEach
    void setUp() {

        //InfoBibliotecaMock
        InfoBiblioteca infoBibliotecaMock = new InfoBiblioteca("correo", "direccion", "telefono", "horario",7, 5, 5);
        infoBibliotecaMock.setId((long)1);

        //retornar info con id 1
        Mockito.when(infoBibliotecaServiceMock.getOne(1)).thenReturn(Optional.of(infoBibliotecaMock));
        //existe con id 1
        Mockito.when(infoBibliotecaServiceMock.existsById(1)).thenReturn(true);
        //no existe con id 2
        Mockito.when(infoBibliotecaServiceMock.existsById(2)).thenReturn(false);
    }

    @Test
    void getByIdExist() { // escenario 1: existe con id 1
        //Arrage
        infoBibliotecaController.setService(infoBibliotecaServiceMock);
        ResponseEntity<InfoBiblioteca> responseServicio;

        //Act
        //se evalua primero que exista la informacion con id 1
        infoBibliotecaServiceMock.existsById(1);
        //se manda la info como respuesta
        responseServicio = infoBibliotecaController.getById(1);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
        Assertions.assertEquals(1, responseServicio.getBody().getId());
        Assertions.assertEquals("correo", responseServicio.getBody().getCorreo());
        Assertions.assertEquals("direccion", responseServicio.getBody().getDireccion());
        Assertions.assertEquals("horario", responseServicio.getBody().getHorario());
        Assertions.assertEquals("telefono", responseServicio.getBody().getTelefono());
        Assertions.assertEquals(5, responseServicio.getBody().getCostoDiaMoroso());
        Assertions.assertEquals(5, responseServicio.getBody().getCostoGeneralPrestamo());
        Assertions.assertEquals(7, responseServicio.getBody().getDiasHabilesPrestamo());
    }

    @Test
    void getByIdNotExist() { //escenario 2 no exist id
        //Arrage
        infoBibliotecaController.setService(infoBibliotecaServiceMock);
        ResponseEntity<InfoBiblioteca> responseServicio;

        //Act
        //se evalua primero que no exista la info con id 2
        infoBibliotecaServiceMock.existsById(2);
        //se manda la info como respuesta
        responseServicio = infoBibliotecaController.getById(2);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }


    @Test
    void updateInfoBibliotecaParemeterBlank() { //escenario 1: parametros en blanco
        //Arrage
        infoBibliotecaController.setService(infoBibliotecaServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        //el fallo se autoprovoca porque el infoBibliotecaDto mock es falso
        responseServicio = infoBibliotecaController.update(1,infoBibliotecaDto);

        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());
    }

    @Test
    void updateInfoBibliotecaIdNotExist() { //escenario 2: la info con id 1 no existe
        //Arrage
        infoBibliotecaController.setService(infoBibliotecaServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        //provocamos que el id no exista para que falle el updaate
        responseServicio = infoBibliotecaController.update(2,infoBibliotecaDto);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }

    @Test
    void updateInfoBibliotecaSuccess() {
        //Arrage
        infoBibliotecaController.setService(infoBibliotecaServiceMock);
        ResponseEntity<?> responseServicio;

        //Act
        //creamos un objeto InfoBiblioteca
        InfoBibliotecaDto infoBibliotecaDto = new InfoBibliotecaDto("correo","direccion","telefono","horario",7,5,5);

        //traemos el InfoBiblioteca mock y le seteamos los nuevos valores del dto
        InfoBiblioteca infoBiblioteca = infoBibliotecaServiceMock.getOne(1).get();
        infoBiblioteca.setCorreo(infoBibliotecaDto.getCorreo());
        infoBiblioteca.setDireccion(infoBibliotecaDto.getDireccion());
        infoBiblioteca.setTelefono(infoBibliotecaDto.getTelefono());
        infoBiblioteca.setHorario(infoBibliotecaDto.getHorario());
        infoBiblioteca.setCostoDiaMoroso(infoBibliotecaDto.getCostoDiaMoroso());
        infoBiblioteca.setCostoGeneralPrestamo(infoBibliotecaDto.getCostoGeneralPrestamo());
        infoBiblioteca.setDiasHabilesPrestamo(infoBibliotecaDto.getDiasHabilesPrestamo());

        responseServicio = infoBibliotecaController.update(1, infoBibliotecaDto);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());

    }
}