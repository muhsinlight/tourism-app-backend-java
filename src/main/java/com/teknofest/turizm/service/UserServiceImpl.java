package com.teknofest.turizm.service;

import com.teknofest.turizm.dto.UserPasswordDto;
import com.teknofest.turizm.exception.ResourceNotFoundException;
import com.teknofest.turizm.model.User;
import com.teknofest.turizm.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName).orElseThrow();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User update(Long id, User user) {
        Optional<User> userDb = userRepository.findById(id);
        if (userDb.isPresent()) {
            User existingUser = userDb.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setBio(user.getBio());
            existingUser.setLocation(user.getLocation());
            existingUser.setInterests(user.getInterests());
            return userRepository.save(existingUser);
        }
        throw new ResourceNotFoundException("Kayıt bulunamadı.");
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kayıt bulunamadı."));
        userRepository.deleteById(id);
    }

}
