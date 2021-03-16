package com.icunoc.biblioteca.controllers;


import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/categorias"})
public class CategoriaController {
    @Autowired
    CategoriaService service;
    //metodo para mandar una lista de libros al cliente
    @GetMapping("/lista")
    public ResponseEntity<List<Categoria>> listarCategorias(){
        List<Categoria> list = service.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }


}
