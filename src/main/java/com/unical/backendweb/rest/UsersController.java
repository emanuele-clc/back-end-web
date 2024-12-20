package com.unical.backendweb.rest;

import com.unical.backendweb.exceptions.user.UserNotFoundException;
import com.unical.backendweb.model.UsersResponse;
import com.unical.backendweb.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // Consenti richieste da Angular
@RestController
@RequestMapping("/api/users") // Usa un percorso più specifico
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Endpoint per ottenere tutti gli utenti o filtrarli per nome, cognome, username
    @GetMapping
    public List<UsersResponse> getUsers(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "cognome", defaultValue = "") String cognome,
            @RequestParam(value = "username", defaultValue = "") String username) {

        if (nome.isEmpty() && cognome.isEmpty() && username.isEmpty()) {
            return usersService.getAllUsers();
        }
        return usersService.filterUsers(nome, cognome, username);
    }

    // Endpoint per ottenere un singolo utente tramite ID
    @GetMapping("/{id}")
    public UsersResponse getUserById(@PathVariable int id) {
        UsersResponse user = usersService.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException("Utente con ID " + id + " non trovato.");
        }

        return user;
    }
}
