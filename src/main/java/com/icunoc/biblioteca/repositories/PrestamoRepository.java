package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    //metodo para buscar un prestamo por codigo de reservacion
    Prestamo findByCodigoReservacion(String codigoReservacion);
    //metodo para buscar un listado de prestamos segun su estado.
    List<Prestamo> findByEstado(String estado);

}
