package com.icunoc.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icunoc.biblioteca.models.Libro;

public interface InterLibroRepo extends JpaRepository<Libro, Integer>{
	

}
