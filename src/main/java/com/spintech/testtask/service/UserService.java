package com.spintech.testtask.service;

import com.spintech.testtask.entity.User;

public interface UserService {
    User findById(Long id);
    User registerUser(String email, String password);
    User findUser(String email, String password);
}

