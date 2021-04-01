package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.PrestamoDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/prestamos"})
public class PrestamoController {
    private static final int DIAS_PRESTAMO=7;
    private static final int DIAS_MOROSO_RESTART=0;
    @Autowired
    PrestamoService service;
    //metodo para mandar una lista de libros al cliente
    @GetMapping("/listaPrestamo/{estado}")
    public ResponseEntity<List<Prestamo>> listarPrestamos(@PathVariable("estado") String estado){
        List<Prestamo> list = service.list(estado);
        for (int i=0; i<list.toArray().length; i++){
            Calendar miFecha = Calendar.getInstance();
            int milisecondsByDay = 86400000;

            int dias = (int) ((miFecha.getTime().getTime()-list.get(i).getFechaInicio().getTime().getTime()) / milisecondsByDay);
            if (dias>DIAS_PRESTAMO){
                int diasAtrasado = dias-DIAS_PRESTAMO;
                System.out.println(diasAtrasado);
                list.get(i).setDiasMoroso(diasAtrasado);
                list.get(i).setMora(true);
            }else{
                list.get(i).setDiasMoroso(DIAS_MOROSO_RESTART);
                list.get(i).setMora(false);
            }
            service.save(list.get(i));
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

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
                prestamoDto.isMora(),
                prestamoDto.getCodigoLibro()
        );
        service.save(nuevoPrestamo);
        return new ResponseEntity(new Mensaje("Se registro correctamente."), HttpStatus.OK);
    }

    public void setService(PrestamoService prestamoService) {
        this.service = prestamoService;
    }
}
