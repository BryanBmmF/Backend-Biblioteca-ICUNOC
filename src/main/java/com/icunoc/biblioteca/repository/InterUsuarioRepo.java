package com.icunoc.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icunoc.biblioteca.security.entity.Usuario;

//interface que hereda de jpa repository para heredar las operaciones basicas de bd
public interface InterUsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	

}
