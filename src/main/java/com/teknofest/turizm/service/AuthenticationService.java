package com.teknofest.turizm.service;

import com.teknofest.turizm.dto.UserPasswordDto;
import com.teknofest.turizm.exception.ResourceNotFoundException;
import com.teknofest.turizm.request.AuthenticationRequest;
import com.teknofest.turizm.response.AuthenticationResponse;
import com.teknofest.turizm.request.RegisterRequest;
import com.teknofest.turizm.model.Role;
import com.teknofest.turizm.model.User;
import com.teknofest.turizm.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
            User user = new User();
            user.setFirstName(request.firstName());
            user.setLastName(request.lastName());
            user.setEmail(request.email());
            user.setPassword(passwordEncoder.encode(request.password()));
            user.setRole(Role.USER);
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken, user.getRole().name());
        }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken, user.getRole().name());
    }
    public void changePassword(Long userId, UserPasswordDto userPasswordDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı"));

        if (!passwordEncoder.matches(userPasswordDto.oldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Eski şifre yanlış!");
        }

        if (userPasswordDto.oldPassword().equals(userPasswordDto.newPassword())) {
            throw new IllegalArgumentException("Eski şifre ile yeni şifre aynı olamaz!");
        }

        String newPasswordHash = passwordEncoder.encode(userPasswordDto.newPassword());
        user.setPassword(newPasswordHash);
        userRepository.save(user);
    }


}
