package com.springboot.swagger.service;

import com.springboot.swagger.dao.UserRepository;
import com.springboot.swagger.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService{


    @Autowired
    private UserRepository userRepository;

    public List<User> findall(){
        return userRepository.findAll();
    }

    public User findAuthorById(long id){
        return userRepository.findUserById(id);
    }
}
