package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

    Optional<Categoria> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
