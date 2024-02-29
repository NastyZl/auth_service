package com.example.guide.controller;

import com.example.guide.dto.ErrorResponse;
import com.example.guide.dto.TokenResponse;
import com.example.guide.dto.User;
import com.example.guide.exception.LoginException;
import com.example.guide.exception.RegistrationException;
import com.example.guide.service.ClientService;
import com.example.guide.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClientService clientService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) throws RegistrationException {
        clientService.register(user.getClientId(), user.getClientSecret());
        return ResponseEntity.ok("Registered");
    }
    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody User user) throws LoginException {
        clientService.checkCredentials(user.getClientId(), user.getClientSecret());
        return new TokenResponse(tokenService.generateToken(user.getClientId()));
    }

    @ExceptionHandler({RegistrationException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(RuntimeException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }


}
