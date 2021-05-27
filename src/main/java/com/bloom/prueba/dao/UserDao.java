package com.bloom.prueba.dao;

import com.bloom.prueba.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository <User,Long>{
    
}
