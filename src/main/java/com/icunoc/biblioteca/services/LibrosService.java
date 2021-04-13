package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Libro;

import java.util.List;
import java.util.Optional;

public interface LibrosService {
    Libro listarId(int id);
    List<Libro> list();
    List<Libro> getByBusqueda(String busqueda);
    Libro add(Libro user);
    Optional<Libro> getOne(int id);
    Optional<Libro> getByNombre(String nombre);
    Optional<Libro> getByCodigo(String codigo);
    void save(Libro user);
    void update(Libro user);
    void delete(int id);
    boolean existsById(int id);
    boolean existsByNombre(String nombre);
    boolean existsByCodigo(String codigo);
}
