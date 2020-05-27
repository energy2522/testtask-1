package com.spintech.testtask.repository;

import org.springframework.data.repository.CrudRepository;

import com.spintech.testtask.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}