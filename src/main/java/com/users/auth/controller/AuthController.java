package com.users.auth.controller;

import com.users.auth.controller.request.AuthRequest;
import com.users.auth.controller.response.AuthResponse;
import com.users.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validate(){
        return ResponseEntity.ok().build();
    }
}
