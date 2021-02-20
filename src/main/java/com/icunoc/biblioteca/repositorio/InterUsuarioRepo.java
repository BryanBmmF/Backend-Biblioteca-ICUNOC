package com.icunoc.biblioteca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icunoc.biblioteca.model.Usuario;

//interface que hereda de jpa repository para heredar las operaciones basicas de bd
public interface InterUsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	

}
