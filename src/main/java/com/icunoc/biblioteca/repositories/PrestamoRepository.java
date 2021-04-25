package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Calendar;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    @Query(value="SELECT count(*) FROM Prestamo p WHERE (p.dpi = ?1 or p.carnet = ?2) AND (p.estado = 'RESERVADO' or p.estado = 'ACTIVO')", nativeQuery = true)
    int countReservacionesPrestamosActivos(String dpi, String carnet);

    @Query(value="SELECT * FROM Prestamo p WHERE (p.dpi LIKE %:busqueda% or p.carnet LIKE %:busqueda% or p.codigoReservacion LIKE %:busqueda%) AND (p.estado = :estado);\n", nativeQuery = true)
    List<Prestamo> findPrestamoByBusquedaAndEstado(@Param("busqueda") String busqueda, @Param("estado") String estado);

    //Bitacora de reservaciones y prestamos segun carnet o dpi
    @Query(value="SELECT * FROM Prestamo p WHERE (p.dpi LIKE %:busqueda% or p.carnet LIKE %:busqueda% );\n", nativeQuery = true)
    List<Prestamo> findPrestamoByBitacora(@Param("busqueda") String busqueda);

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
