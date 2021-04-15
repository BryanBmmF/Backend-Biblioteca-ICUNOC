package com.icunoc.biblioteca.services;

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
    @BeforeEach
    void setUp() {
        Prestamo prestamoMock = new Prestamo();
        prestamoMock.setId(1);
        prestamoMock.setNombre("Luis");
        prestamoMock.setApellido("Hernandez");
        prestamoMock.setDpi("1234567891234");
        prestamoMock.setCarnet("201631722");
        prestamoMock.setCarrera("SISTEMAS");
        prestamoMock.setFechaReservacion(fechaActual);
        prestamoMock.setFechaInicio(fechaActual);
        prestamoMock.setFechaFin(fechaActual);
        prestamoMock.setCosto(0);
        prestamoMock.setEstado("RESERVADO");
        prestamoMock.setCodigoReservacion("1234ABCD");
        prestamoMock.setCodigoLibro("123ABC");

        List<Prestamo> miListMock = Arrays.asList(prestamoMock);

        Mockito.when(prestamoRepository.findByCodigoReservacion("1234ABCD")).thenReturn(prestamoMock);
        Mockito.when(prestamoRepository.findByCarnet("201631722")).thenReturn(List.of(prestamoMock));
        Mockito.when(prestamoRepository.findByDpi("1234567891234")).thenReturn(List.of(prestamoMock));
        Mockito.when(prestamoRepository.findByFechaInicio(fechaActual)).thenReturn(List.of(prestamoMock));
        Mockito.when(prestamoRepository.findByEstado("RESERVADO")).thenReturn(List.of(prestamoMock));
        Mockito.when(prestamoRepository.countReservacionesPrestamosActivos("1234567891234", "201631722")).thenReturn(2);
    }

    @Test
    void listarCodigoReservacion() {
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        //Act
        Prestamo prestamoRecivido = prestamoServiceImpl.listarCodigoReservacion("1234ABCD");
        //Assert
        Assertions.assertEquals("1234ABCD",prestamoRecivido.getCodigoReservacion());
    }

    @Test
    void getOne(){
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        //Act
        Prestamo prestamoRecivido = prestamoServiceImpl.getOne("1234ABCD");
        //Assert
        Assertions.assertEquals("1234ABCD",prestamoRecivido.getCodigoReservacion());
    }

    @Test
    void countReservacionesPrestamosActivos(){
        //Arrange
        prestamoServiceImpl.setRepository(prestamoRepository);
        //Act
        int conteo = prestamoServiceImpl.countReservacionesPrestamosActivos("1234567891234", "201631722");
        //Assert
        Assertions.assertEquals(conteo,2);
    }

    @Test
    void listarCarnet(){
        //Arrage
        prestamoServiceImpl.setRepository(prestamoRepository);
        List<Prestamo> listPrestamo;
        //Act
        listPrestamo = prestamoServiceImpl.listarCarnet("201631722");
        //Assert
        Assertions.assertEquals("Luis", listPrestamo.get(0).getNombre());
    }

    @Test
    void listarDPI(){
        //Arrage
        prestamoServiceImpl.setRepository(prestamoRepository);
        List<Prestamo> listPrestamo;
        //Act
        listPrestamo = prestamoServiceImpl.listarDPI("1234567891234");
        //Assert
        Assertions.assertEquals("1234567891234", listPrestamo.get(0).getDpi());
    }

    @Test
    void list(){
        //Arrage
        prestamoServiceImpl.setRepository(prestamoRepository);
        List<Prestamo> listPrestamo;
        //Act
        listPrestamo = prestamoServiceImpl.listPorEstado("RESERVADO");
        //Assert
        Assertions.assertEquals("RESERVADO", listPrestamo.get(0).getEstado());
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


}