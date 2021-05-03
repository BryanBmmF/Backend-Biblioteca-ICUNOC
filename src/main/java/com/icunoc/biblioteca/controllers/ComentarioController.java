package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.dto.ComentarioDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Comentario;
import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.services.ComentarioService;
import com.icunoc.biblioteca.services.PrestamoServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/comentarios"})
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    PrestamoServiceImpl prestamoService;

    @GetMapping("/lista/{id}")
    public ResponseEntity<List<Comentario>> listByBook(@PathVariable("id") String id) {
        List<Comentario> lista = comentarioService.listByBook(id);
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @PostMapping("/crearComentario")
    public ResponseEntity<Comentario> create(@RequestBody ComentarioDto comentarioDto) {
        if(StringUtils.isBlank(comentarioDto.getNombre()) || StringUtils.isBlank(comentarioDto.getComentario()) || StringUtils.isBlank(comentarioDto.getIdentificacion())) {
            return new ResponseEntity(new Mensaje("Todos los campos son obligatorios"), HttpStatus.BAD_REQUEST);
        }
        List<Prestamo> listaPrestamos = prestamoService.findByCarnetOrDPIOfBook(comentarioDto.getIdentificacion(),comentarioDto.getIdLibro(), AppConstants.ESTADO_FINALIZADO);
        if(listaPrestamos.isEmpty())
            return new ResponseEntity(new Mensaje("No se ha realizado ninguna reservación de este libro con este documento de identificacion"),HttpStatus.NOT_FOUND);

        Comentario comentario = new Comentario(comentarioDto.getId(),
                comentarioDto.getNombre(),
                comentarioDto.getPuntuacion(),
                comentarioDto.getComentario(),
                comentarioDto.getFecha(),
                comentarioDto.getIdLibro());

        comentarioService.save(comentario);
        return new ResponseEntity(new Mensaje("El comentario se guardo con exito"),HttpStatus.OK);
    }

    public void injectMocks(ComentarioService comentario, PrestamoServiceImpl prestamo) {
        this.comentarioService = comentario;
        this.prestamoService = prestamo;
    }
}
