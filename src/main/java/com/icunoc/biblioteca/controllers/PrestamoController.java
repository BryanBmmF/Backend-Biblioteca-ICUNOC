package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.PrestamoDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.PrestamoServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/prestamos"})
public class PrestamoController {
    @Autowired
    PrestamoServiceImpl service;

    @GetMapping(path = {"/{codigoReservacion}"})
    public Prestamo listarReservacion(@PathVariable("codigoReservacion") String codigo){
        return service.listarCodigoReservacion(codigo);
    }

    //Nuevo: se pasa un Json Libro por medio de http y se valida
    @PostMapping("/crearReservacion")
    public ResponseEntity<?> create(@RequestBody PrestamoDto prestamoDto){
        // guardar reservacion
        Prestamo nuevoPrestamo = new Prestamo(
                prestamoDto.getNombre(),
                prestamoDto.getApellido(),
                prestamoDto.getDpi(),
                prestamoDto.getCarnet(),
                prestamoDto.getCarrera(),
                prestamoDto.getFechaReservacion(),
                prestamoDto.getEstado(),
                prestamoDto.getCodigoReservacion(),
                prestamoDto.getCodigoLibro()
        );
        service.save(nuevoPrestamo);
        return new ResponseEntity(new Mensaje("Se registro correctamente."), HttpStatus.OK);

    }
}
