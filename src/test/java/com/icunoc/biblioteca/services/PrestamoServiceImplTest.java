package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.repositories.PrestamoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

class PrestamoServiceImplTest {

    @Autowired
    PrestamoRepository prestamoRepository = Mockito.mock(PrestamoRepository.class);

    PrestamoServiceImpl prestamoServiceImpl = new PrestamoServiceImpl();

    Calendar fechaActual = Calendar.getInstance();

    private static final String DPI_TEST = "1234567891234";
    private static final String CARNET_TEST = "201631722";
    private static final String CODIGO_RESERVACION_TEST = "1234ABCD";
    private static final String CODIGO_LIBRO_TEST = "123ABC";

    @BeforeEach
    void setUp() {
        Prestamo prestamoMock = new Prestamo();
        prestamoMock.setId(1);
        prestamoMock.setNombre("Luis");
        prestamoMock.setApellido("Hernandez");
        prestamoMock.setDpi(DPI_TEST);
        prestamoMock.setCarnet(CARNET_TEST);
        prestamoMock.setCarrera("SISTEMAS");
        prestamoMock.setFechaReservacion(fechaActual);
        prestamoMock.setFechaInicio(fechaActual);
        prestamoMock.setFechaFin(fechaActual);
        prestamoMock.setCosto(0);
        prestamoMock.setEstado(AppConstants.ESTADO_RESERVADO);
        prestamoMock.setCodigoReservacion(CODIGO_RESERVACION_TEST);
        prestamoMock.setCodigoLibro("123ABC");

        List<Prestamo> miListMock = Arrays.asList(prestamoMock);

        Mockito.when(prestamoRepository.findByCodigoReservacion(CODIGO_RESERVACION_TEST)).thenReturn(prestamoMock);
        Mockito.when(prestamoRepository.findByCarnet(CARNET_TEST)).thenReturn(List.of(prestamoMock));
        Mockito.when(prestamoRepository.findByDpi(DPI_TEST)).thenReturn(List.of(prestamoMock));
        Mockito.when(prestamoRepository.findByFechaInicio(fechaActual)).thenReturn(List.of(prestamoMock));
        Mockito.when(prestamoRepository.findByEstado(AppConstants.ESTADO_RESERVADO)).thenReturn(List.of(prestamoMock));
        Mockito.when(prestamoRepository.countReservacionesPrestamosActivos(DPI_TEST, CARNET_TEST)).thenReturn(2);
        Mockito.when(prestamoRepository.findPrestamoByBusquedaAndEstado("1",AppConstants.ESTADO_RESERVADO)).thenReturn(miListMock);
    }

    @Test
    void listarCodigoReservacion() {
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        //Act
        Prestamo prestamoRecivido = prestamoServiceImpl.listarCodigoReservacion(CODIGO_RESERVACION_TEST);
        //Assert
        Assertions.assertEquals(CODIGO_RESERVACION_TEST,prestamoRecivido.getCodigoReservacion());
    }

    @Test
    void getOne(){
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        //Act
        Prestamo prestamoRecivido = prestamoServiceImpl.getOne(CODIGO_RESERVACION_TEST);
        //Assert
        Assertions.assertEquals(CODIGO_RESERVACION_TEST,prestamoRecivido.getCodigoReservacion());
    }

    @Test
    void countReservacionesPrestamosActivos(){
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        //Act
        int conteo = prestamoServiceImpl.countReservacionesPrestamosActivos(DPI_TEST, CARNET_TEST);
        //Assert
        Assertions.assertEquals(conteo,2);
    }

    @Test
    void listarCarnet(){
        //Arrage
        prestamoServiceImpl.setRepository(prestamoRepository);
        List<Prestamo> listPrestamo;
        //Act
        listPrestamo = prestamoServiceImpl.listarCarnet(CARNET_TEST);
        //Assert
        Assertions.assertEquals("Luis", listPrestamo.get(0).getNombre());
    }

    @Test
    void listarDPI(){
        //Arrage
        prestamoServiceImpl.setRepository(prestamoRepository);
        List<Prestamo> listPrestamo;
        //Act
        listPrestamo = prestamoServiceImpl.listarDPI(DPI_TEST);
        //Assert
        Assertions.assertEquals(DPI_TEST, listPrestamo.get(0).getDpi());
    }

    @Test
    void list(){
        //Arrage
        prestamoServiceImpl.setRepository(prestamoRepository);
        List<Prestamo> listPrestamo;
        //Act
        listPrestamo = prestamoServiceImpl.listPorEstado(AppConstants.ESTADO_RESERVADO);
        //Assert
        Assertions.assertEquals(AppConstants.ESTADO_RESERVADO, listPrestamo.get(0).getEstado());
    }

    @Test
    void listarFechaInicio(){
        //Arrage
        prestamoServiceImpl.setRepository(prestamoRepository);
        List<Prestamo> listPrestamo;
        //Act
        listPrestamo = prestamoServiceImpl.listarFechaInicio(fechaActual);
        //Assert
        Assertions.assertEquals(fechaActual, listPrestamo.get(0).getFechaInicio());
    }

    @Test
    void save(){
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        Prestamo newPrestamo = new Prestamo();
        //Act
        prestamoServiceImpl.save(newPrestamo);
    }

    @Test
    void delete(){
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        //Act
        prestamoServiceImpl.delete(1);
    }

    @Test
    void findPrestamoByBusquedaAndEstado() {
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        List<Prestamo> prestamos;
        //Act
        prestamos = prestamoServiceImpl.findPrestamoByBusquedaAndEstado("1", AppConstants.ESTADO_RESERVADO);
        //Assert
        Assertions.assertEquals("Luis", prestamos.get(0).getNombre());
    }

}