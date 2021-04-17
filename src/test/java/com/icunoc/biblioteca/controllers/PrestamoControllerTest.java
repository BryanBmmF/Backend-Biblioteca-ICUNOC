package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.PrestamoDto;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.dto.InfoBibliotecaDto;
import com.icunoc.biblioteca.models.*;
import com.icunoc.biblioteca.services.InfoBibliotecaService;
import com.icunoc.biblioteca.services.PrestamoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.*;

class PrestamoControllerTest {
    //mock service de infoBiblioteca
    @Autowired
    InfoBibliotecaService infoBibliotecaServiceMock = Mockito.mock(InfoBibliotecaService.class);

    //mock de infoBiblitecaDto
    InfoBibliotecaDto infoBibliotecaDto = Mockito.mock(InfoBibliotecaDto.class);

    //contrller
    @Autowired
    InfoBibliotecaController infoBibliotecaController = new InfoBibliotecaController();
    @Autowired
    PrestamoService prestamoServiceMock = Mockito.mock(PrestamoService.class);
    @Autowired
    PrestamoController prestamoController = new PrestamoController();
    //userDto Mock
    @Autowired
    PrestamoDto prestamoDto = Mockito.mock(PrestamoDto.class);

    Prestamo prestamoMock = Mockito.mock(Prestamo.class);

    @BeforeEach
    void setUp() {
        //InfoBibliotecaMock
        InfoBiblioteca infoBibliotecaMock = new InfoBiblioteca("correo", "direccion", "telefono", "horario",7, 5, 5);
        infoBibliotecaMock.setId((long)1);

        //retornar info con id 1
        Mockito.when(infoBibliotecaServiceMock.getOne(1)).thenReturn(Optional.of(infoBibliotecaMock));
        //existe con id 1
        Mockito.when(infoBibliotecaServiceMock.existsById(1)).thenReturn(true);

        Calendar miFecha = Calendar.getInstance();

        Calendar moraFecha = new GregorianCalendar(2021, Calendar.MARCH, 22, 23, 11, 44);
        Calendar sinMoraFecha = new GregorianCalendar(2021, Calendar.APRIL, 14, 23, 11, 44);
        //mock para validar que un prestamo tiene mora
        prestamoMock = new Prestamo();
        prestamoMock.setId(1);
        prestamoMock.setNombre("Brayan");
        prestamoMock.setApellido("Monzon");
        prestamoMock.setDpi("1234567891234");
        prestamoMock.setCarnet("201631722");
        prestamoMock.setCarrera("SISTEMAS");
        prestamoMock.setFechaReservacion(null);
        prestamoMock.setFechaInicio(moraFecha);
        prestamoMock.setFechaFin(null);
        prestamoMock.setCosto(0);
        prestamoMock.setEstado("ACTIVO");
        prestamoMock.setCodigoReservacion("1234ABCD");
        prestamoMock.setMora(true);
        prestamoMock.setDiasMoroso(1);
        prestamoMock.setCodigoLibro("123ABC");

        //mock para validar que un prestamo NO tiene mora
        Prestamo prestamoMock2 = new Prestamo();
        prestamoMock2.setId(1);
        prestamoMock2.setNombre("Jony");
        prestamoMock2.setApellido("Chiroy");
        prestamoMock2.setDpi("1234567891234");
        prestamoMock2.setCarnet("201631722");
        prestamoMock2.setCarrera("SISTEMAS");
        prestamoMock2.setFechaReservacion(miFecha);
        prestamoMock2.setFechaInicio(sinMoraFecha);
        prestamoMock2.setFechaFin(miFecha);
        prestamoMock2.setCosto(0);
        prestamoMock2.setEstado("ACTIVO");
        prestamoMock2.setCodigoReservacion("1234ABCD");
        prestamoMock2.setMora(true);
        prestamoMock2.setDiasMoroso(12);
        prestamoMock2.setCodigoLibro("123ABC");

        //mock para validar que un prestamo sigue vigente
        Prestamo prestamoMock3 = new Prestamo();
        prestamoMock3.setId(2);
        prestamoMock3.setNombre("Ricardo");
        prestamoMock3.setApellido("Mendez");
        prestamoMock3.setDpi("1234567891234");
        prestamoMock3.setCarnet("201631722");
        prestamoMock3.setCarrera("SISTEMAS");
        prestamoMock3.setFechaReservacion(miFecha);
        prestamoMock3.setFechaInicio(sinMoraFecha);
        prestamoMock3.setFechaFin(miFecha);
        prestamoMock3.setCosto(0);
        prestamoMock3.setEstado("RESERVADO");
        prestamoMock3.setCodigoReservacion("1234ABCD");
        prestamoMock3.setMora(true);
        prestamoMock3.setDiasMoroso(12);
        prestamoMock3.setCodigoLibro("123ABC");

        //mock para validar que un prestamo NO sigue vigente
        //NO BORRAR SERVIRA MAS ADELANTE
       Prestamo prestamoMock4 = new Prestamo();
        prestamoMock4.setId(2);
        prestamoMock4.setNombre("Juan Pablo");
        prestamoMock4.setApellido("Valiente");
        prestamoMock4.setDpi("1234567891234");
        prestamoMock4.setCarnet("201631722");
        prestamoMock4.setCarrera("SISTEMAS");
        prestamoMock4.setFechaReservacion(moraFecha);
        prestamoMock4.setFechaInicio(moraFecha);
        prestamoMock4.setFechaFin(miFecha);
        prestamoMock4.setCosto(0);
        prestamoMock4.setEstado("RESERVADO");
        prestamoMock4.setCodigoReservacion("1234ABCD");
        prestamoMock4.setMora(true);
        prestamoMock4.setDiasMoroso(12);
        prestamoMock4.setCodigoLibro("123ABC");

        List<Prestamo> miListMock;
        miListMock = Arrays.asList(prestamoMock,prestamoMock2,prestamoMock3,prestamoMock4);
        List<Prestamo> miListMock2;
        miListMock2 = Arrays.asList(prestamoMock);

        Mockito.when(prestamoServiceMock.listarCodigoReservacion("1234ABCD")).thenReturn(prestamoMock);
        Mockito.when(prestamoServiceMock.listPorEstado("ACTIVO")).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.listPorEstado("RESERVADO")).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.listarCarnet("201631722")).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.listarDPI("1234567891234")).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.listarFechaInicio(miFecha)).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.countReservacionesPrestamosActivos("1234567891234","201631722")).thenReturn(2);
        Mockito.when(prestamoServiceMock.findPrestamoByBusquedaAndEstado("1","ACTIVO")).thenReturn(miListMock2);
    }

    @Test
    void listarReservacion() {
        //Arrange
        Prestamo respuestaServicio = new Prestamo();
        prestamoController.setPrestamoService(prestamoServiceMock);
        //Act
        respuestaServicio = prestamoController.listarReservacion("1234ABCD");
        //Assert
        Assertions.assertEquals(1, respuestaServicio.getId());
        Assertions.assertEquals("Brayan", respuestaServicio.getNombre());
        Assertions.assertEquals("Monzon", respuestaServicio.getApellido());
        Assertions.assertEquals("1234567891234", respuestaServicio.getDpi());
        Assertions.assertEquals("201631722", respuestaServicio.getCarnet());
        Assertions.assertEquals("SISTEMAS", respuestaServicio.getCarrera());
        Assertions.assertEquals(0, respuestaServicio.getCosto());
        Assertions.assertEquals("ACTIVO", respuestaServicio.getEstado());
        Assertions.assertEquals("1234ABCD", respuestaServicio.getCodigoReservacion());
        Assertions.assertEquals(true, respuestaServicio.isMora());
        Assertions.assertEquals(1, respuestaServicio.getDiasMoroso());
        Assertions.assertEquals("123ABC", respuestaServicio.getCodigoLibro());
    }

    @Test
    void create() {
        ResponseEntity<?> respuesta;
        prestamoController.setPrestamoService(prestamoServiceMock);
        //respuesta = prestamoController.create()
        PrestamoDto prestamoMockDto = new PrestamoDto();
        prestamoMockDto.setId(1);
        prestamoMockDto.setNombre("Luis");
        prestamoMockDto.setApellido("Hernandez");
        prestamoMockDto.setDpi("1234567891234");
        prestamoMockDto.setCarnet("201631722");
        prestamoMockDto.setCarrera("SISTEMAS");
        prestamoMockDto.setFechaReservacion(null);
        prestamoMockDto.setFechaInicio(null);
        prestamoMockDto.setFechaFin(null);
        prestamoMockDto.setCosto(0);
        prestamoMockDto.setEstado("RESERVADO");
        prestamoMockDto.setCodigoReservacion("1234ABCD");
        prestamoMockDto.setMora(true);
        prestamoMockDto.setDiasMoroso(1);
        prestamoMockDto.setCodigoLibro("123ABC");
        respuesta = prestamoController.create(prestamoMockDto);
        Assertions.assertEquals(200,respuesta.getStatusCodeValue());
    }

    //Test para la busqueda general por estado
    @Test
    void listarPrestamo(){
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoController.setService(infoBibliotecaServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarPrestamo(true,"ACTIVO");
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
    @Test
    void reporte1(){
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoController.setService(infoBibliotecaServiceMock);
        ResponseEntity<List<Object>> responseServicio;
        responseServicio = prestamoController.reporte1();
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void reporte3(){
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoController.setService(infoBibliotecaServiceMock);
        ResponseEntity<List<Object>> responseServicio;
        responseServicio = prestamoController.reporte3();
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    //Test para la busqueda general por estado
    @Test
    void listarActivo(){
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoController.setService(infoBibliotecaServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarPrestamos("ACTIVO");
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void listarReservado(){
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoController.setService(infoBibliotecaServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarPrestamos("RESERVADO");
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void delete(){
        //Arrage
        prestamoController.setPrestamoService(prestamoServiceMock);
        ResponseEntity<?> responseServicio;
        //Act
        prestamoServiceMock.delete(1);
        responseServicio = prestamoController.delete(1);
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    //testa para finalizar un prestamo y cambiar su estado
    @Test
    void finalizarPrestamo(){
        //arrange
        Mockito.when(prestamoServiceMock.getOne("1234ABCD")).thenReturn(prestamoMock);
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoMock.setEstado("FINALIZADO");
        prestamoMock.setFechaFin(null);
        //act
        ResponseEntity<?> response = prestamoController.finalizarPrestamo("1234ABCD");
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    //testa para iniciar un prestamo y cambiar su estado
    @Test
    void iniciarPrestamo(){
        //arrange
        Mockito.when(prestamoServiceMock.getOne("1234ABCD")).thenReturn(prestamoMock);
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoMock.setEstado("ACTIVO");
        prestamoMock.setFechaFin(null);
        //act
        ResponseEntity<?> response = prestamoController.iniciarPrestamo("1234ABCD");
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void contarPrestamosReservacionesActivas(){
        //Arrange
        prestamoController.setPrestamoService(prestamoServiceMock);
        ResponseEntity<Integer> responseServicio;
        //Act
        responseServicio = prestamoController.contarPrestamosReservacionesActivas("1234567891234", "201631722");
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void listarPrestamosPorBusquedayEstado() {
        //Arrange
        prestamoController.setPrestamoService(prestamoServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        //Act
        responseServicio = prestamoController.listarPrestamosPorBusquedayEstado("1", "ACTIVO");
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
}
