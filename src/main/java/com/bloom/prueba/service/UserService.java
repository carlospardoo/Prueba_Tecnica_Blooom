package com.bloom.prueba.service;

import java.util.ArrayList;
import java.util.Optional;

import com.bloom.prueba.dao.UserDao;
import com.bloom.prueba.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserDao usuarioDAO;

    public ArrayList<User> obtenerUsuarios(){
        return (ArrayList<User>)usuarioDAO.findAll();
    }
    
    public User guardarUsuario(User usuario){
        return (User)usuarioDAO.save(usuario);
    }

    public Optional<User> obtenerUsuario(Long id){
        return (Optional<User>)usuarioDAO.findById(id);
    }

    public void borrarUsuario(User usuario){
        usuarioDAO.delete(usuario);
    }

}
