package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    List<Comentario> findByIdLibro(String idLibro);
}
