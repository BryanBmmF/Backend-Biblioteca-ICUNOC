package com.icunoc.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icunoc.biblioteca.model.Libro;

public interface InterLibroRepo extends JpaRepository<Libro, Integer>{
	

}
