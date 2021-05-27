package com.bloom.prueba.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.bloom.prueba.model.User;
import com.bloom.prueba.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService usuarioServicio;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/listar")
    public ArrayList<User> listarUsuarios(){
        return usuarioServicio.obtenerUsuarios();
    }

    @PostMapping("/agregar")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User crearUsuario(@RequestBody User usuario){
        logger.info("Proceso de Insercion");
        Optional<User> usuar = usuarioServicio.obtenerUsuario(usuario.getCedula());
        logger.info("DATA: [ "+"cedula = "+usuario.getCedula()+", nombre:"+usuario.getNombre()+" ]");
        if (!usuar.isEmpty()){
            logger.error("Ya existe registro con la cedula definida");
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        logger.info("Creado Usuario con cedula: "+usuario.getCedula()+", nombre: "+usuario.getNombre());
        return usuarioServicio.guardarUsuario(usuario);
    }

    @PutMapping("/actualizar")
    @ResponseStatus(HttpStatus.CREATED)
    public User actualizarUsuario(@RequestBody User usuario){
        logger.info("Proceso de Actualizacion");

        Optional<User> usuar = usuarioServicio.obtenerUsuario(usuario.getCedula());
        
        
        if (usuar.isEmpty()){
            logger.error("No existe registro con cedula "+usuario.getCedula()+" para actualizar");
            logger.info("DATA FALLIDA: [ "+"cedula = "+usuario.getCedula()+", nombre:"+usuario.getNombre()+" ]");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        logger.info("DATA ANTERIOR: [ "+"cedula = "+usuar.get().getCedula()+", nombre:"+usuar.get().getNombre()+" ]");


        logger.info("Modificado Usuario con cedula = "+usuario.getCedula());
        logger.info("DATA NUEVA: [ "+"cedula = "+usuario.getCedula()+", nombre = "+usuario.getNombre()+" ]");
        return usuarioServicio.guardarUsuario(usuario);
    }

    @DeleteMapping("/borrar")
    public void borrarUsuario(@RequestBody User usuario){
        usuarioServicio.borrarUsuario(usuario);
    }
}
