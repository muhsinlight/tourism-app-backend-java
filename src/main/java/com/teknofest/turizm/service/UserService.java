package com.teknofest.turizm.service;

import com.teknofest.turizm.dto.UserPasswordDto;
import com.teknofest.turizm.model.User;

import java.util.Optional;

public interface UserService {
    User findByEmail(String email);
    User findByUserName(String userName);
    Optional<User> findUserById(Long id);
    User save(User user);
    User update(Long id, User user);
    void delete(Long id);
    UserPasswordDto changePassword(Long id, UserPasswordDto userPasswordDto);
}
