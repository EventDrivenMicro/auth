package com.users.auth.service;

import com.users.auth.controller.request.AuthRequest;
import com.users.auth.controller.response.AuthResponse;
import com.users.auth.exception.InvalidCredentialsException;
import com.users.auth.jwt.JwtTokenProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthResponse login(AuthRequest authRequest){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );
        Authentication result = authenticationManager.authenticate(authentication);
        if(result.isAuthenticated()) {
            String jwtToken = jwtTokenProvider.generateToken(result);
            return AuthResponse.builder().status(Boolean.TRUE).token(jwtToken).build();
        }
        throw new InvalidCredentialsException("Kullanici adi veya sifre hatali");
    }
}
