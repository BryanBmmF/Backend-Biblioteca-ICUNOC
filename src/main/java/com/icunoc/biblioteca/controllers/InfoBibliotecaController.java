package com.icunoc.biblioteca.controllers;


import com.icunoc.biblioteca.dto.InfoBibliotecaDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.InfoBiblioteca;
import com.icunoc.biblioteca.services.InfoBibliotecaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api/v1/admin/biblioteca"})
public class InfoBibliotecaController {

    @Autowired
    InfoBibliotecaService service;

    //busqueda de user por id
    @GetMapping("/detailId/{id}")
    public ResponseEntity<InfoBiblioteca> getById(@PathVariable("id") long id){
        //evaluamos si existe el usuario por id
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No esta disponible la información de la Biblioteca"), HttpStatus.NOT_FOUND);
        InfoBiblioteca infoBiblioteca = service.getOne(id).get();
        return new ResponseEntity(infoBiblioteca, HttpStatus.OK);

    }

    //actualizacion
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody InfoBibliotecaDto bibliotecaDto){
        //evaluar si existe el libro
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No esta disponible la información de la Biblioteca"), HttpStatus.NOT_FOUND);
        //validar campos no nulos
        if(StringUtils.isBlank(bibliotecaDto.getCorreo()) |
                StringUtils.isBlank(bibliotecaDto.getDireccion())|
                StringUtils.isBlank(bibliotecaDto.getHorario())|
                StringUtils.isBlank(bibliotecaDto.getTelefono()))
            return new ResponseEntity(new Mensaje("Todos los campos son Obligatorios"), HttpStatus.BAD_REQUEST);

        // si no hay problemas se guarda la informacion
        InfoBiblioteca infoBiblioteca = service.getOne(id).get();
        infoBiblioteca.setCorreo(bibliotecaDto.getCorreo());
        infoBiblioteca.setDireccion(bibliotecaDto.getDireccion());
        infoBiblioteca.setTelefono(bibliotecaDto.getTelefono());
        infoBiblioteca.setHorario(bibliotecaDto.getHorario());
        infoBiblioteca.setCostoDiaMoroso(bibliotecaDto.getCostoDiaMoroso());
        infoBiblioteca.setDiasHabilesPrestamo(bibliotecaDto.getDiasHabilesPrestamo());
        infoBiblioteca.setCostoGeneralPrestamo(bibliotecaDto.getCostoGeneralPrestamo());
        service.update(infoBiblioteca);
        return new ResponseEntity(new Mensaje("La informacion de Biblioteca se actualizó correctamente !!!"), HttpStatus.OK);
    }

    //para el mock
    public void setService(InfoBibliotecaService bibliotecaService){
        this.service = bibliotecaService;
    }

}
