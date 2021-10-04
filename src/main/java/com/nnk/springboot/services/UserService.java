package com.nnk.springboot.services;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.PasswordPatternException;
import com.nnk.springboot.exceptions.UsernameExistException;

import java.util.List;

public interface UserService {

    User save(User user) throws PasswordPatternException, UsernameExistException;

    void delete(User savedUser);
    public User findById(Integer id) ;
    public List<User> findAll();
}
