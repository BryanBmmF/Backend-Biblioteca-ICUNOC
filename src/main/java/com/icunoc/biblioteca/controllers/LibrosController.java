package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.services.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/detalleslibros"})
public class LibrosController {

    @Autowired
    LibrosService service;

    @GetMapping(path = {"/{idLibro}"})
    public Libro listarId(@PathVariable("idLibro") int id){
        return service.listarId(id);
    }

}
