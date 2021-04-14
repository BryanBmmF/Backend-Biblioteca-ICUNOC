package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.Param;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    @Query(value="SELECT count(*) FROM prestamo p WHERE (p.dpi = ?1 or p.carnet = ?2) AND (p.estado = 'RESERVADO' or p.estado = 'ACTIVO')", nativeQuery = true)
    int countReservacionesPrestamosActivos(String dpi, String carnet);

    //metodo para buscar un prestamo por codigo de reservacion
    Prestamo findByCodigoReservacion(String codigoReservacion);
    List<Prestamo> findByCarnet(String carnet);
    List<Prestamo> findByDpi(String dpi);
    List<Prestamo> findByFechaInicio(Calendar fechaInicio);
    //metodo para buscar un listado de prestamos segun su estado.
    List<Prestamo> findByEstado(String estado);

}
