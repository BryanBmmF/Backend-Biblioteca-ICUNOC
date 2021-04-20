package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.InfoBibliotecaDto;
import com.icunoc.biblioteca.models.InfoBiblioteca;
import com.icunoc.biblioteca.services.InfoBibliotecaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

class InfoBibliotecaControllerTest {

    private static final String CORREO = "correo";
    private static final String DIRECCION = "direccion";
    private static final String TELEFONO = "telefono";
    private static final String HORARIO = "horario";

    //mock service de infoBiblioteca
    @Autowired
    InfoBibliotecaService infoBibliotecaServiceMock = Mockito.mock(InfoBibliotecaService.class);

    //mock de infoBiblitecaDto
    InfoBibliotecaDto infoBibliotecaDto = Mockito.mock(InfoBibliotecaDto.class);

    //contrller
    @Autowired
    InfoBibliotecaController infoBibliotecaController = new InfoBibliotecaController();

    //un DTO prueba
    InfoBibliotecaDto infoBibliotecaDtoPrueba;

    @BeforeEach
    void setUp() {

        //InfoBibliotecaMock
        InfoBiblioteca infoBibliotecaMock = new InfoBiblioteca(CORREO, DIRECCION, TELEFONO, HORARIO,7, 5, 5);
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
        infoBibliotecaDtoPrueba = new InfoBibliotecaDto("correo","direccion","telefono","horario",7,5,5);

        //traemos el InfoBiblioteca mock y le seteamos los nuevos valores del dto
        InfoBiblioteca infoBiblioteca = infoBibliotecaServiceMock.getOne(1).get();
        infoBiblioteca.setCorreo(infoBibliotecaDtoPrueba.getCorreo());
        infoBiblioteca.setDireccion(infoBibliotecaDtoPrueba.getDireccion());
        infoBiblioteca.setTelefono(infoBibliotecaDtoPrueba.getTelefono());
        infoBiblioteca.setHorario(infoBibliotecaDtoPrueba.getHorario());
        infoBiblioteca.setCostoDiaMoroso(infoBibliotecaDtoPrueba.getCostoDiaMoroso());
        infoBiblioteca.setCostoGeneralPrestamo(infoBibliotecaDtoPrueba.getCostoGeneralPrestamo());
        infoBiblioteca.setDiasHabilesPrestamo(infoBibliotecaDtoPrueba.getDiasHabilesPrestamo());

        responseServicio = infoBibliotecaController.update(1, infoBibliotecaDtoPrueba);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());

    }
}