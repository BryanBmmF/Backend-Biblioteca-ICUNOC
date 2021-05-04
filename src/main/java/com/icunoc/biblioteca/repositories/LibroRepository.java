package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{

    @Query(value="SELECT * FROM Libro l WHERE l.autor LIKE %:busqueda% OR l.codigo LIKE %:busqueda% OR l.nombre LIKE %:busqueda%", nativeQuery = true)
    List<Libro> findLibroByBusqueda(@Param("busqueda") String busqueda);

    //metodo extra para buscar libros por nombre
    Optional<Libro> findByNombre(String nombre);
    Optional<Libro> findByCodigo(String codigo);
    Optional<Libro> findByAutor(String autor);
    //metodo extra para saber si un libro existe
    boolean existsByNombre(String nombre);
    boolean existsByAutor(String autor);
    boolean existsByCodigo(String codigo);
    Libro findByIdLibro(int id);
}
