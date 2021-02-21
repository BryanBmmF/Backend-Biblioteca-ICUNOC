package com.icunoc.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icunoc.biblioteca.model.Libro;
import com.icunoc.biblioteca.repository.InterLibroRepo;

//Clase de MVC que redirecciona las peticiones de usuario hacia una logica de negocios
@Controller
public class GreetingController {
	
	//Inyeccion de libro
	@Autowired
	private InterLibroRepo repo;
	
	//Tipo de peticion get con un parametro nombre
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		
		//Logica de negocios sencilla que inserta un libro
		Libro lib = new Libro();
		lib.setNombre("Libro Prueba 1");
		repo.save(lib);
		
		//atributo que recuperara la vista greeting
		model.addAttribute("name", name);
		
		//busca la pagina a la cual se desea navegar
		return "greeting";
	}
	
	//Listar de la base de datos los libros
	@GetMapping("/listar")
	public String greeting( Model model) {

		//Logica de negocios sencilla para recuperar libros de la base de datos
		model.addAttribute("libros", repo.findAll());

		//busca la pagina a la cual se desea navegar
		return "greeting";
	}


}