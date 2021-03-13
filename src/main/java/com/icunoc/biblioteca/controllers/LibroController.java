package com.icunoc.biblioteca.controllers;


import com.icunoc.biblioteca.dto.LibroDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.services.CategoriaService;
import com.icunoc.biblioteca.services.LibrosService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/ingresoLibro"})
public class LibroController {
    @Autowired
    LibrosService service;
    CategoriaService categoryService;
    //metodo para mandar una lista de libros al cliente
    @GetMapping("/listaLibro")
    public ResponseEntity<List<Libro>> listarLibro(){
        List<Libro> list = service.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //busqueda de libro por id
    @GetMapping("/detalleLibro/{id}")
    public ResponseEntity<Libro> getById(@PathVariable("id") int id){
        //evaluamos si existe el usuario por id
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el Libro"), HttpStatus.NOT_FOUND);
        Libro libro = service.getOne(id).get();
        return new ResponseEntity(libro, HttpStatus.OK);
    }

    //busqueda de user por nombre
    @GetMapping("/detalleNombre/{nombre}")
    public ResponseEntity<Libro> getByNombre(@PathVariable("nombre") String nombre){
        //evaluamos si existe el usuario por nombre
        if(!service.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("No existe el Libro"), HttpStatus.NOT_FOUND);
        Libro libro = service.getByNombre(nombre).get();
        return new ResponseEntity(libro, HttpStatus.OK);
    }

    //Nuevo: se pasa un Json Libro por medio de http y se valida
    @PostMapping("/crearLibro")
    public ResponseEntity<?> create(@RequestBody LibroDto libroDto){
        //validar campos no nulos
        if(StringUtils.isBlank(libroDto.getNombre())) {
            System.out.println("VIENDO QUE MANDA APPI REST: " + libroDto.getNombre());
            return new ResponseEntity(new Mensaje("Todos los campos son Obligatorios"), HttpStatus.BAD_REQUEST);
        }
        //validar que no exista el libro a registrar
        if(service.existsByNombre(libroDto.getNombre()))
            return new ResponseEntity(new Mensaje("El Libro que intenta registrar ya existe"), HttpStatus.BAD_REQUEST);

        // guardar libro
        System.out.println("VIENDO QUE AUTOR VIENE: " + libroDto.getAutor());
        System.out.println("VIENDO QUE IDIOMA VIENE: " + libroDto.getIdioma());
        System.out.println("VIENDO QUE CATEGORIA VIENE: " + libroDto.getIdCategoria());
        Libro libro = new Libro(
                libroDto.getAutor(),
                libroDto.getCodigo(),
                libroDto.getEdicion(),
                libroDto.getFechaPublicacion(),
                libroDto.getIdioma(),
                libroDto.getNombre(),
                libroDto.getPathImagen(),
                libroDto.getStock(),
                libroDto.getIdCategoria());
        service.save(libro);
        return new ResponseEntity(new Mensaje("El libro se registro correctamente !!!"), HttpStatus.OK);
    }

    //actualizacion
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody LibroDto libroDto){
        //evaluar si existe el libro
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el Libro"), HttpStatus.NOT_FOUND);

        //validar campos no nulos
        if(StringUtils.isBlank(libroDto.getNombre()) |
                StringUtils.isBlank(libroDto.getAutor()))
            return new ResponseEntity(new Mensaje("Todos los campos son Obligatorios"), HttpStatus.BAD_REQUEST);

        //comprobar que no se quiera actualizar a un libro existente
        if(service.existsByNombre(libroDto.getNombre()) && service.getByNombre(libroDto.getNombre()).get().getIdLibro() != id)
            return new ResponseEntity(new Mensaje("El nuevo libro que intenta actualizar ya existe"), HttpStatus.BAD_REQUEST);

        // si no hay problemas se guarda el usuario
        Libro libro = service.getOne(id).get();
        libro.setNombre(libroDto.getNombre());
        libro.setAutor(libroDto.getAutor());
        libro.setStock(libroDto.getStock());
        libro.setEdicion(libroDto.getEdicion());
        libro.setFechaPublicacion(libroDto.getFechaPublicacion());
        libro.setIdioma(libroDto.getIdioma());
        libro.setPathImagen(libroDto.getPathImagen());
        service.save(libro);
        return new ResponseEntity(new Mensaje("El libro se actualizó correctamente !!!"), HttpStatus.OK);
    }

    //eliminar
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //comprobamos que exista
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el libro"), HttpStatus.NOT_FOUND);

        service.delete(id);
        return new ResponseEntity(new Mensaje("El libro se elimino correctamente !!!"), HttpStatus.OK);
    }
}
