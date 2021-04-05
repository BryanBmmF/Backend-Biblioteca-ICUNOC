package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.AsignacionLibroDto;
import com.icunoc.biblioteca.dto.CategoriaDto;
import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.models.AsignacionLibro;
import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.services.AsignacionLibroService;
import com.icunoc.biblioteca.services.CategoriaService;
import com.icunoc.biblioteca.services.LibrosService;
import com.icunoc.biblioteca.services.LibrosServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/asignacionlibros"})
public class AsignacionLibroController {

    @Autowired
    private AsignacionLibroService service;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LibrosServiceImpl librosService;

    @GetMapping("/lista")
    public ResponseEntity<List<AsignacionLibro>> list(){
        List<AsignacionLibro> list = service.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/categoriasLibro/{id}")
    public ResponseEntity<List<Categoria>> findCategoriesOfBook(@PathVariable("id") int id) {
        if(!librosService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El libro que busca no esta registrado"), HttpStatus.NOT_FOUND);
        }
        List<AsignacionLibro> list = service.listByBook(id);
        List<Categoria> categoriesList = new ArrayList<>();
        for (AsignacionLibro asignacion : list) {
            categoriesList.add(categoriaService.find(asignacion.getIdCategoria()).get());
        }

        return new ResponseEntity(categoriesList,HttpStatus.OK);
    }

    @GetMapping("/librosCategoria/{id}")
    public ResponseEntity<List<Categoria>> findBooksOfCategory(@PathVariable("id") int id) {
        if(!categoriaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("La categoria que busca no esta registrada"), HttpStatus.NOT_FOUND);
        }
        List<AsignacionLibro> list = service.listByCategory(id);
        List<Libro> bookList = new ArrayList<>();
        for (AsignacionLibro asignacion : list) {
            System.out.printf(asignacion.getIdAsignacionLibro()+"");
            bookList.add(librosService.getOne(asignacion.getIdLibro()).get());
        }

        return new ResponseEntity(bookList,HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<AsignacionLibro> find(@PathVariable("id") int id) {
        if(!service.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe la asignación del libro"), HttpStatus.NOT_FOUND);
        }
        AsignacionLibro asignacion = service.find(id).get();
        return new ResponseEntity(asignacion,HttpStatus.OK);
    }

    @PostMapping("/crearAsignacion")
    public ResponseEntity<AsignacionLibro> create(@RequestBody AsignacionLibroDto asignacion) {
        AsignacionLibro nuevaAsignacion = new AsignacionLibro(
                asignacion.getId(),
                asignacion.getIdCategoria(),
                asignacion.getIdLibro()
        );
        service.save(nuevaAsignacion);
        return new ResponseEntity(new Mensaje("Se asigno el libro a la categoria correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //comprobamos que exista
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID de la asignacion"), HttpStatus.NOT_FOUND);

        service.delete(id);
        return new ResponseEntity(new Mensaje("La asignacion se elimino correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/eliminarAsignaciones/{id}")
    public ResponseEntity<?> deleteAssignations(@PathVariable("id") int id){
        //comprobamos que exista
        if(!librosService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID del libro"), HttpStatus.NOT_FOUND);

        service.deleteBookAssignation(id);
        return new ResponseEntity(new Mensaje("Las asignaciones se eliminaron correctamente"), HttpStatus.OK);
    }

    public void setService(AsignacionLibroService service) {this.service = service;}

}
