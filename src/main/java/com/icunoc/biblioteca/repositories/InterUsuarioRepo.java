package com.icunoc.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icunoc.biblioteca.models.Usuario;

//interface que hereda de jpa repository para heredar las operaciones basicas de bd
public interface InterUsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	

}
