package com.icunoc.biblioteca.controllers;


import com.icunoc.biblioteca.dto.LibroDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.services.LibrosService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/ingresoLibro"})
public class LibroController {

    private byte[] imagenBytes;
    private static final String NO_EXISTE = "No existe el Libro";

    @Autowired
    LibrosService service;

    @PostMapping("/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        this.imagenBytes = file.getBytes();
    }

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
            return new ResponseEntity(new Mensaje(NO_EXISTE), HttpStatus.NOT_FOUND);
        Libro libro = service.getOne(id).get();
        return new ResponseEntity(libro, HttpStatus.OK);
    }

    //busqueda de libro por codigo
    @GetMapping("/detalleLibroC/{codigo}")
    public ResponseEntity<Libro> getByNombre(@PathVariable("codigo") String nombre){
        //evaluamos si existe el usuario por nombre
        if(!service.existsByCodigo(nombre))
            return new ResponseEntity(new Mensaje(NO_EXISTE), HttpStatus.NOT_FOUND);
        Libro libro = service.getByCodigo(nombre).get();
        return new ResponseEntity(libro, HttpStatus.OK);
    }

    //Nuevo: se pasa un Json Libro por medio de http y se valida
    @PostMapping("/crearLibro")
    public ResponseEntity<Mensaje> create(@RequestBody LibroDto libroDto){
        //validar campos no nulos
        if(StringUtils.isBlank(libroDto.getNombre())) {
            return new ResponseEntity(new Mensaje("Todos los campos son Obligatorios"), HttpStatus.BAD_REQUEST);
        }
        //validar que no exista el libro a registrar
        if(service.existsByCodigo(libroDto.getCodigo()))
            return new ResponseEntity(new Mensaje("El libro que intentas registrar ya existe."), HttpStatus.BAD_REQUEST);
        // guardar libro
        Libro libro = new Libro(
                libroDto.getAutor(),
                libroDto.getCodigo(),
                libroDto.getEdicion(),
                libroDto.getFechaPublicacion(),
                libroDto.getIdioma(),
                libroDto.getNombre(),
                this.imagenBytes,
                libroDto.getStock());
        service.save(libro);
        this.imagenBytes = null;
        return new ResponseEntity(libro, HttpStatus.OK);

    }

    //actualizacion
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody LibroDto libroDto){
        //evaluar si existe el libro
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje(NO_EXISTE), HttpStatus.NOT_FOUND);
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
        if (this.imagenBytes != null){
            libro.setPathImagen(this.imagenBytes);
        }
        service.save(libro);
        this.imagenBytes = null;
        return new ResponseEntity(new Mensaje("El libro se actualizó correctamente !!!"), HttpStatus.OK);
    }

    //eliminar
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id){
        //comprobamos que exista
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el libro"), HttpStatus.NOT_FOUND);
        service.delete(id);
        return new ResponseEntity(new Mensaje("El libro se elimino correctamente !!!"), HttpStatus.OK);
    }

    //metodo para mandar una lista de libros al cliente
    @GetMapping("/librosFiltrados/{busqueda}")
    public ResponseEntity<List<Libro>> listarLibrosPorBusqueda(@PathVariable("busqueda") String busqueda){
        List<Libro> list = service.getByBusqueda(busqueda);
        return new ResponseEntity(list, HttpStatus.OK);
    }


    public void setService(LibrosService service){
        this.service=service;
    }
}
