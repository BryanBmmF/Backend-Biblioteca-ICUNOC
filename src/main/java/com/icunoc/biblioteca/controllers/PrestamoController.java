package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.PrestamoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/reserva"})
public class PrestamoController {
    @Autowired
    PrestamoServiceImpl service;

    @GetMapping(path = {"/{codigoReservacion}"})
    public Prestamo listarReservacion(@PathVariable("codigoReservacion") String codigo){
        return service.listarCodigoReservacion(codigo);
    }
}
