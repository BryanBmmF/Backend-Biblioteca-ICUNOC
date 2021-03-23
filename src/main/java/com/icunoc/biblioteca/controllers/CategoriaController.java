package com.icunoc.biblioteca.controllers;


import com.icunoc.biblioteca.dto.CategoriaDto;
import com.icunoc.biblioteca.dto.LibroDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.services.CategoriaService;
import org.apache.commons.lang3.StringUtils;
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

    @GetMapping("/lista/{id}")
    public ResponseEntity<Categoria> find(@PathVariable("id") int id) {
        if(!service.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el Libro"), HttpStatus.NOT_FOUND);
        }
        Categoria categoria = service.find(id).get();
        return new ResponseEntity(categoria,HttpStatus.OK);
    }

    @PostMapping("/crearCategoria")
    public ResponseEntity<Categoria> create(@RequestBody CategoriaDto categoriaDto) {
        if(StringUtils.isBlank(categoriaDto.getNombre())) {
            return new ResponseEntity(new Mensaje("Todos los campos son obligatorios"), HttpStatus.BAD_REQUEST);
        }
        if(service.existsByNombre(categoriaDto.getNombre()))
            return new ResponseEntity(new Mensaje("La categoria que intenta registrar ya existe"), HttpStatus.BAD_REQUEST);

        Categoria categoria = new Categoria(
                categoriaDto.getId(),
                categoriaDto.getNombre(),
                categoriaDto.getDescripcion()
        );
        service.save(categoria);
        return new ResponseEntity(new Mensaje("La categoría se registro correctamente."), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody CategoriaDto categoriaDto){
        //evaluar si existe el libro
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe la categoria"), HttpStatus.NOT_FOUND);

        //validar campos no nulos
        if(StringUtils.isBlank(categoriaDto.getNombre())
                | StringUtils.isBlank(categoriaDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("Todos los campos son Obligatorios"), HttpStatus.BAD_REQUEST);

        //comprobar que no se quiera actualizar a un libro existente
        if(service.existsByNombre(categoriaDto.getNombre()) && service.getByNombre(categoriaDto.getNombre()).get().getIdCategoria() != id)
            return new ResponseEntity(new Mensaje("La nueva categoria que se esta intentando actualizar ya existe"), HttpStatus.BAD_REQUEST);

        // si no hay problemas se guarda el usuario
        Categoria categoria = service.find(id).get();
        categoria.setNombre(categoriaDto.getNombre());
        categoria.setDescripcion(categoriaDto.getDescripcion());
        service.save(categoria);
        return new ResponseEntity(new Mensaje("La categoria se actualizó correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //comprobamos que exista
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe la categoria para eliminar"), HttpStatus.NOT_FOUND);

        service.delete(id);
        return new ResponseEntity(new Mensaje("El libro se elimino correctamente"), HttpStatus.OK);
    }

    public void setService(CategoriaService service) {
        this.service = service;
    }

}
