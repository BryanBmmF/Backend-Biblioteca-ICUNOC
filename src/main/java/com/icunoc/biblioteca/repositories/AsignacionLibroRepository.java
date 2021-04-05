package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.AsignacionLibro;
import com.icunoc.biblioteca.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AsignacionLibroRepository extends JpaRepository<AsignacionLibro, Integer> {

    List<AsignacionLibro> findAsignacionLibroByIdLibro(int idLibro);
    List<AsignacionLibro> findAsignacionLibroByIdCategoria(int idCategoria);
    void deleteAsignacionLibroByIdLibro(int idLibro);
}
