package com.icunoc.biblioteca.scheduleds;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.enums.Idioma;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.LibrosService;
import com.icunoc.biblioteca.services.PrestamoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ScheduledTasksTest {

    @Autowired
    PrestamoService prestamoServiceMock = Mockito.mock(PrestamoService.class);

    @Autowired
    LibrosService librosServiceMock = Mockito.mock(LibrosService.class);

    @Autowired
    ScheduledTasks scheduledTasks = new ScheduledTasks();

    Prestamo prestamoMock = Mockito.mock(Prestamo.class);

    @BeforeEach
    void setUp() {
        Calendar nuevoCalendar = new GregorianCalendar(2021,0,31);
        //mock para validar que un prestamo tiene mora
        prestamoMock = new Prestamo();
        prestamoMock.setId(1);
        prestamoMock.setNombre("Brayan");
        prestamoMock.setApellido("Monzon");
        prestamoMock.setDpi("1234567891234");
        prestamoMock.setCarnet("201631722");
        prestamoMock.setCarrera("SISTEMAS");
        prestamoMock.setFechaReservacion(nuevoCalendar);
        prestamoMock.setFechaFin(null);
        prestamoMock.setCosto(0);
        prestamoMock.setEstado("RESERVADO");
        prestamoMock.setCodigoReservacion("1234ABCD");
        prestamoMock.setMora(true);
        prestamoMock.setDiasMoroso(1);
        prestamoMock.setCodigoLibro("L1");

        Libro libroMock = new Libro();

        libroMock.setIdLibro(1);
        libroMock.setCodigo("L1");
        libroMock.setNombre("Ecuaciones");
        libroMock.setAutor("Luis Hernandez");
        libroMock.setStock(5);
        libroMock.setEdicion(5);
        libroMock.setIdioma(Idioma.ESPAÑOL);

        List<Prestamo> miListMock;
        miListMock = Arrays.asList(prestamoMock);

        Mockito.when(prestamoServiceMock.listPorEstado(AppConstants.ESTADO_RESERVADO)).thenReturn(miListMock);
        Mockito.when(librosServiceMock.getByCodigo("L1")).thenReturn(Optional.of(libroMock));
    }

    @Test
    void scheduleTaskVerificacionExpiracion() {
        //Arrange
        scheduledTasks.setPrestamoService(prestamoServiceMock);
        scheduledTasks.setLibrosService(librosServiceMock);
        //Act
        scheduledTasks.scheduleTaskVerificacionExpiracion();
        //Assert
    }


}