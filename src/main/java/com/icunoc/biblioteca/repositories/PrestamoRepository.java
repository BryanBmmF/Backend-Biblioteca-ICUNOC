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

    @Query(value="SELECT count(*) FROM Prestamo p WHERE (p.dpi = ?1 or p.carnet = ?2) AND (p.estado = 'RESERVADO' or p.estado = 'ACTIVO')", nativeQuery = true)
    int countReservacionesPrestamosActivos(String dpi, String carnet);

    //metodo para buscar un prestamo por codigo de reservacion
    Prestamo findByCodigoReservacion(String codigoReservacion);
    List<Prestamo> findByCarnet(String carnet);
    List<Prestamo> findByDpi(String dpi);
    List<Prestamo> findByFechaInicio(Calendar fechaInicio);
    List<Prestamo> findByMoraAndEstado(boolean mora, String estado);
    //metodo para buscar un listado de prestamos segun su estado.
    List<Prestamo> findByEstado(String estado);

    //querys para los reportes
    @Query(value = "SELECT nombre,apellido, carnet, dpi,carrera ,SUM(costo) AS TotalPagado, count(*) As VecesMoroso \n" +
            "FROM Prestamo WHERE mora=1 AND estado='FINALIZADO' GROUP BY nombre, apellido, carnet, dpi, carrera Having Count(*) >=1 order by TotalPagado DESC\n", nativeQuery = true)
    List<Object> reporte3();

    @Query(value = "SELECT count(*) AS TotalPrestamos,SUM(costo) AS TotalRecaudado FROM Prestamo  WHERE mora = 1  AND estado='FINALIZADO' order by TotalRecaudado DESC\n", nativeQuery = true)
    List<Object> reporte1();


}
