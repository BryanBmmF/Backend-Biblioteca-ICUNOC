package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface PrestamoService {
    Prestamo listarCodigoReservacion(String codigo);
    List<Prestamo> listarCarnet(String carnet);
    List<Prestamo> listarDPI(String dpi);
    List<Prestamo> listarFechaInicio(Calendar fechaInicio);
    void save(Prestamo prestamo);
    List<Prestamo> list(String estado);
    Prestamo getOne(String id);

}

