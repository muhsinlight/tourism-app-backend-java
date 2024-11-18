package com.teknofest.turizm.service;

import com.teknofest.turizm.model.User;
import com.teknofest.turizm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> userDb = userRepository.findById(id);
        if (userDb.isPresent()) {
            return userRepository.save(user);
        }
        // TODO: code obviously exception
        throw new IllegalStateException("Hata");
    }

    @Override
    public void delete(Long id) {
        Optional<User> userDb = userRepository.findById(id);
        userDb.ifPresent(user -> userRepository.deleteById(user.getId()));
    }
}
