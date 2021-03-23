package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.Mensaje;
import com.icunoc.biblioteca.dto.UserDto;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api/v1/admin/usuarios"})
public class UserController {

    @Autowired
    UserService service;

    //metodo para mandar una lista de usuarios al cliente
    @GetMapping("/lista")
    public ResponseEntity<List<User>> listar(){
        List<User> list = service.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //busqueda de user por id
    @GetMapping("/detailId/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") long id){
        //evaluamos si existe el usuario por id
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el Usuario"), HttpStatus.NOT_FOUND);
        User user = service.getOne(id).get();
        return new ResponseEntity(user, HttpStatus.OK);

    }

    //busqueda de user por nombre
    @GetMapping("/detailNombre/{nombre}")
    public ResponseEntity<User> getByNombre(@PathVariable("nombre") String nombre){
        //evaluamos si existe el usuario por nombre
        if(!service.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("No existe el Usuario"), HttpStatus.NOT_FOUND);
        User user = service.getByNombre(nombre).get();
        return new ResponseEntity(user, HttpStatus.OK);
    }
    //busqueda de user
    @GetMapping("/detailUsername/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable("username") String username){
        //evaluamos si existe el usuario por nombre
        if(!service.existsByUsername(username))
            return new ResponseEntity(new Mensaje("No existe el Usuario"), HttpStatus.NOT_FOUND);
        User user = service.getByUsername(username).get();
        return new ResponseEntity(user, HttpStatus.OK);

    }

    //Nuevo: se pasa un Json User por medio de http y se valida
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto){
        //validar campos no nulos
        if(StringUtils.isBlank(userDto.getUsername()) |
                StringUtils.isBlank(userDto.getPassword()) |
                StringUtils.isBlank(userDto.getNombre()) |
                StringUtils.isBlank(userDto.getNumeroRegistro()))
            return new ResponseEntity(new Mensaje("Todos los campos son Obligatorios y no deben ser solo espacios en blanco!"), HttpStatus.BAD_REQUEST);

        //validar que no exista el usuario a registrar
        if(service.existsByUsername(userDto.getUsername()))
            return new ResponseEntity(new Mensaje("El Usuario que intenta registrar ya existe"), HttpStatus.BAD_REQUEST);

        // guardar usuario
        User user = new User(userDto.getNombre(), userDto.getNumeroRegistro(), userDto.getUsername(), userDto.getPassword(), userDto.getTipo());
        service.save(user);
        return new ResponseEntity(new Mensaje("El Usuario se registro correctamente !!!"), HttpStatus.OK);
    }

    //actualizacion
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody UserDto userDto){
        //evaluar si existe el usuario
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el Usuario"), HttpStatus.NOT_FOUND);

        //validar campos no nulos
        if(StringUtils.isBlank(userDto.getUsername()) |
                StringUtils.isBlank(userDto.getPassword()) |
                StringUtils.isBlank(userDto.getNombre()) |
                StringUtils.isBlank(userDto.getNumeroRegistro()))
            return new ResponseEntity(new Mensaje("Todos los campos son Obligatorios y no deben ser solo espacios en blanco!"), HttpStatus.BAD_REQUEST);

        //comprobar que no se quiera actualizar a un username existente
        if(service.existsByUsername(userDto.getUsername()) && service.getByUsername(userDto.getUsername()).get().getId() != id)
            return new ResponseEntity(new Mensaje("El nuevo Usuario que intenta actualizar ya existe"), HttpStatus.BAD_REQUEST);

        // si no hay problemas se actualiza el usuario
        User user = service.getOne(id).get();
        user.setNombre(userDto.getNombre());
        user.setNumeroRegistro(userDto.getNumeroRegistro());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setTipo(userDto.getTipo());
        service.update(user);
        return new ResponseEntity(new Mensaje("El Usuario se actualizó correctamente !!!"), HttpStatus.OK);
    }

    //eliminar
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        //comprobamos que exista
        if(!service.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el Usuario"), HttpStatus.NOT_FOUND);

        service.delete(id);
        return new ResponseEntity(new Mensaje("El Usuario se elimino correctamente !!!"), HttpStatus.OK);
    }

    //para el mock
    public void setService(UserService userService){
        this.service = userService;
    }
}
