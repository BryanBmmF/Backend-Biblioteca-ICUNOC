package com.icunoc.biblioteca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icunoc.biblioteca.model.Libro;

public interface InterLibroRepo extends JpaRepository<Libro, Integer>{
	

}
