package com.teknofest.turizm.service;

import com.teknofest.turizm.model.User;

import java.util.Optional;

public interface UserService {
    User findByEmail(String email);
    Optional<User> findUserById(Long id);
    User save(User user);
    User update(Long id, User user);
    void delete(Long id);
}
