package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/usuarios"})
public class UserController {

    @Autowired
    UserService service;

    //metodo para guardar el usuario
    @PostMapping
    public User agregar(@RequestBody User user){
        return  service.add(user);
    }
}
