package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{

    //metodo extra para buscar libros por nombre
    Optional<Libro> findByNombre(String nombre);
    Optional<Libro> findByAutor(String autor);
    //metodo extra para saber si un libro existe
    boolean existsByNombre(String nombre);
    boolean existsByAutor(String autor);
    boolean existsByCodigo(String codigo);
    Libro findByIdLibro(int id);
}
