package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.constants.AppConstants;
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

    private static final String DPI_TEST = "1234567891234";
    private static final String CARNET_TEST = "201631722";
    private static final String CARRERA_TEST = "SISTEMAS";
    private static final String CODIGO_RESERVACION_TEST = "1234ABCD";
    private static final String CODIGO_LIBRO_TEST = "123ABC";

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
        prestamoMock.setDpi(DPI_TEST);
        prestamoMock.setCarnet(CARNET_TEST);
        prestamoMock.setCarrera(CARRERA_TEST);
        prestamoMock.setFechaReservacion(null);
        prestamoMock.setFechaInicio(moraFecha);
        prestamoMock.setFechaFin(null);
        prestamoMock.setCosto(0);
        prestamoMock.setEstado(AppConstants.ESTADO_ACTIVO);
        prestamoMock.setCodigoReservacion(CODIGO_RESERVACION_TEST);
        prestamoMock.setMora(true);
        prestamoMock.setDiasMoroso(1);
        prestamoMock.setCodigoLibro(CODIGO_LIBRO_TEST);

        //mock para validar que un prestamo NO tiene mora
        Prestamo prestamoMock2 = new Prestamo();
        prestamoMock2.setId(1);
        prestamoMock2.setNombre("Jony");
        prestamoMock2.setApellido("Chiroy");
        prestamoMock2.setDpi(DPI_TEST);
        prestamoMock2.setCarnet(CARNET_TEST);
        prestamoMock2.setCarrera(CARRERA_TEST);
        prestamoMock2.setFechaReservacion(miFecha);
        prestamoMock2.setFechaInicio(sinMoraFecha);
        prestamoMock2.setFechaFin(miFecha);
        prestamoMock2.setCosto(0);
        prestamoMock2.setEstado(AppConstants.ESTADO_ACTIVO);
        prestamoMock2.setCodigoReservacion(CODIGO_RESERVACION_TEST);
        prestamoMock2.setMora(true);
        prestamoMock2.setDiasMoroso(12);
        prestamoMock2.setCodigoLibro(CODIGO_LIBRO_TEST);

        //mock para validar que un prestamo sigue vigente
        Prestamo prestamoMock3 = new Prestamo();
        prestamoMock3.setId(2);
        prestamoMock3.setNombre("Ricardo");
        prestamoMock3.setApellido("Mendez");
        prestamoMock3.setDpi(DPI_TEST);
        prestamoMock3.setCarnet(CARNET_TEST);
        prestamoMock3.setCarrera(CARRERA_TEST);
        prestamoMock3.setFechaReservacion(miFecha);
        prestamoMock3.setFechaInicio(sinMoraFecha);
        prestamoMock3.setFechaFin(miFecha);
        prestamoMock3.setCosto(0);
        prestamoMock3.setEstado(AppConstants.ESTADO_RESERVADO);
        prestamoMock3.setCodigoReservacion(CODIGO_RESERVACION_TEST);
        prestamoMock3.setMora(true);
        prestamoMock3.setDiasMoroso(12);
        prestamoMock3.setCodigoLibro(CODIGO_LIBRO_TEST);

        //mock para validar que un prestamo NO sigue vigente
        //NO BORRAR SERVIRA MAS ADELANTE
       Prestamo prestamoMock4 = new Prestamo();
        prestamoMock4.setId(2);
        prestamoMock4.setNombre("Juan Pablo");
        prestamoMock4.setApellido("Valiente");
        prestamoMock4.setDpi(DPI_TEST);
        prestamoMock4.setCarnet(CARNET_TEST);
        prestamoMock4.setCarrera(CARRERA_TEST);
        prestamoMock4.setFechaReservacion(moraFecha);
        prestamoMock4.setFechaInicio(moraFecha);
        prestamoMock4.setFechaFin(miFecha);
        prestamoMock4.setCosto(0);
        prestamoMock4.setEstado(AppConstants.ESTADO_RESERVADO);
        prestamoMock4.setCodigoReservacion(CODIGO_RESERVACION_TEST);
        prestamoMock4.setMora(true);
        prestamoMock4.setDiasMoroso(12);
        prestamoMock4.setCodigoLibro(CODIGO_LIBRO_TEST);

        List<Prestamo> miListMock;
        miListMock = Arrays.asList(prestamoMock,prestamoMock2,prestamoMock3,prestamoMock4);
        List<Prestamo> miListMock2;
        miListMock2 = Arrays.asList(prestamoMock);

        Mockito.when(prestamoServiceMock.listarCodigoReservacion(CODIGO_RESERVACION_TEST)).thenReturn(prestamoMock);
        Mockito.when(prestamoServiceMock.listPorEstado(AppConstants.ESTADO_ACTIVO)).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.listPorEstado(AppConstants.ESTADO_RESERVADO)).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.listarCarnet(CARNET_TEST)).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.listarDPI(DPI_TEST)).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.listarFechaInicio(miFecha)).thenReturn(miListMock);
        Mockito.when(prestamoServiceMock.countReservacionesPrestamosActivos(DPI_TEST,CARNET_TEST)).thenReturn(2);
        Mockito.when(prestamoServiceMock.findPrestamoByBusquedaAndEstado("1",AppConstants.ESTADO_ACTIVO)).thenReturn(miListMock2);
        Mockito.when(prestamoServiceMock.findPrestamoByBitacora("1")).thenReturn(miListMock2);
    }

    @Test
    void listarReservacion() {
        //Arrange
        Prestamo respuestaServicio;
        prestamoController.setPrestamoService(prestamoServiceMock);
        //Act
        respuestaServicio = prestamoController.listarReservacion(CODIGO_RESERVACION_TEST);
        //Assert
        Assertions.assertEquals(1, respuestaServicio.getId());
        Assertions.assertEquals("Brayan", respuestaServicio.getNombre());
        Assertions.assertEquals("Monzon", respuestaServicio.getApellido());
        Assertions.assertEquals(DPI_TEST, respuestaServicio.getDpi());
        Assertions.assertEquals(CARNET_TEST, respuestaServicio.getCarnet());
        Assertions.assertEquals(CARRERA_TEST, respuestaServicio.getCarrera());
        Assertions.assertEquals(0, respuestaServicio.getCosto());
        Assertions.assertEquals(AppConstants.ESTADO_ACTIVO, respuestaServicio.getEstado());
        Assertions.assertEquals(CODIGO_RESERVACION_TEST, respuestaServicio.getCodigoReservacion());
        Assertions.assertEquals(true, respuestaServicio.isMora());
        Assertions.assertEquals(1, respuestaServicio.getDiasMoroso());
        Assertions.assertEquals(CODIGO_LIBRO_TEST, respuestaServicio.getCodigoLibro());
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
        prestamoMockDto.setDpi(DPI_TEST);
        prestamoMockDto.setCarnet(CARNET_TEST);
        prestamoMockDto.setCarrera(CARRERA_TEST);
        prestamoMockDto.setFechaReservacion(null);
        prestamoMockDto.setFechaInicio(null);
        prestamoMockDto.setFechaFin(null);
        prestamoMockDto.setCosto(0);
        prestamoMockDto.setEstado(AppConstants.ESTADO_RESERVADO);
        prestamoMockDto.setCodigoReservacion(CODIGO_RESERVACION_TEST);
        prestamoMockDto.setMora(true);
        prestamoMockDto.setDiasMoroso(1);
        prestamoMockDto.setCodigoLibro(CODIGO_LIBRO_TEST);
        respuesta = prestamoController.create(prestamoMockDto);
        Assertions.assertEquals(200,respuesta.getStatusCodeValue());
    }

    //Test para la busqueda general por estado
    @Test
    void listarPrestamo(){
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoController.setService(infoBibliotecaServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarPrestamo(true,AppConstants.ESTADO_ACTIVO);
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
        responseServicio = prestamoController.listarPrestamos(AppConstants.ESTADO_ACTIVO);
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void listarReservado(){
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoController.setService(infoBibliotecaServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        responseServicio = prestamoController.listarPrestamos(AppConstants.ESTADO_RESERVADO);
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
        Mockito.when(prestamoServiceMock.getOne(CODIGO_RESERVACION_TEST)).thenReturn(prestamoMock);
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoMock.setEstado("FINALIZADO");
        prestamoMock.setFechaFin(null);
        //act
        ResponseEntity<?> response = prestamoController.finalizarPrestamo(CODIGO_RESERVACION_TEST);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    //testa para iniciar un prestamo y cambiar su estado
    @Test
    void iniciarPrestamo(){
        //arrange
        Mockito.when(prestamoServiceMock.getOne(CODIGO_RESERVACION_TEST)).thenReturn(prestamoMock);
        prestamoController.setPrestamoService(prestamoServiceMock);
        prestamoMock.setEstado(AppConstants.ESTADO_ACTIVO);
        prestamoMock.setFechaFin(null);
        //act
        ResponseEntity<?> response = prestamoController.iniciarPrestamo(CODIGO_RESERVACION_TEST);
        //assert
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void contarPrestamosReservacionesActivas(){
        //Arrange
        prestamoController.setPrestamoService(prestamoServiceMock);
        ResponseEntity<Integer> responseServicio;
        //Act
        responseServicio = prestamoController.contarPrestamosReservacionesActivas(DPI_TEST, CARNET_TEST);
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void listarPrestamosPorBusquedayEstado() {
        //Arrange
        prestamoController.setPrestamoService(prestamoServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        //Act
        responseServicio = prestamoController.listarPrestamosPorBusquedayEstado("1", AppConstants.ESTADO_ACTIVO);
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void listarPrestamosPorBitacora() {
        //Arrange
        prestamoController.setPrestamoService(prestamoServiceMock);
        ResponseEntity<List<Prestamo>> responseServicio;
        //Act
        responseServicio = prestamoController.listarPrestamosPorBitacora("1");
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
}
