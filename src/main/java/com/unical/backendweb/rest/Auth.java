package com.unical.backendweb.rest;

import com.unical.backendweb.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated // Abilito la validazione creata sul DTO
@RestController
@RequestMapping("/api/auth/")
public class Auth {

    // todo: update placeholder
    private Object service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid User user) {
        return ResponseEntity.ok(user.getNome());
    }
}
