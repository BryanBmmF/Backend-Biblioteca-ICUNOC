package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    //metodo extra para buscar libros por nombre
    Optional<Prestamo> findByNombre(String nombre);
    //metodo extra para saber si un libro existe
    boolean existsByCodigoReservacion(String nombre);
    Prestamo findByCodigoReservacion(String codigoReservacion);
}
