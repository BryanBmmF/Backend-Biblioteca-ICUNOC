package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoService {
    Prestamo listarCodigoReservacion(String codigo);

    List<Prestamo> list();
    Prestamo add(Prestamo prestamo);
    Optional<Prestamo> getOne(int id);
    Optional<Prestamo> getByCodigoReservacion(String codigo);
    void save(Prestamo prestamo);
    void update(Prestamo prestamo);
    void delete(int id);
    boolean existsById(int id);
    boolean existsByCodigoReservacion(String codigo);
}

