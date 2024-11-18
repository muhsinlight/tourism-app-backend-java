package com.teknofest.turizm.controller;

import com.teknofest.turizm.response.ApiResponse;
import com.teknofest.turizm.request.AuthenticationRequest;
import com.teknofest.turizm.response.AuthenticationResponse;
import com.teknofest.turizm.service.AuthenticationService;
import com.teknofest.turizm.request.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@RequestBody RegisterRequest request) {
        var result = authenticationService.register(request);
        var response = new ApiResponse.Builder<AuthenticationResponse>()
                .success(true)
                .message("Kayıt başarılı")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.login(request);
        var response = new ApiResponse.Builder<AuthenticationResponse>()
                .success(true)
                .message("Giriş başarılı")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
