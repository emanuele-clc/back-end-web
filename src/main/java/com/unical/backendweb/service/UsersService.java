package com.unical.backendweb.service;

import com.unical.backendweb.model.UsersResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    // Lista statica di utenti per simulare il database
    private List<UsersResponse> users = new ArrayList<>();

    // Inizializzare con alcuni utenti di esempio
    public UsersService() {
        users.add(createUserResponse(1L, "Massimo", "Bossetti", "bossetinho", "massimo.bossetti@email.com", 12345, "/assets/image/campo1.jpg", 1));
        users.add(createUserResponse(2L, "Filippo", "Turetta", "philipTurets", "filippo.turetta@email.com", 67890, "/assets/image/campo2.jpg", 2));
        users.add(createUserResponse(3L, "Pietro", "Pacciani", "ilmostro", "pietro.pacciani@email.com", 13579, "/assets/image/campo3.jpg", 3));
    }

    // Ottieni tutti gli utenti
    public List<UsersResponse> getAllUsers() {
        return users;
    }

    // Filtra gli utenti in base ai parametri
    public List<UsersResponse> filterUsers(String nome, String cognome, String username) {
        return users.stream()
                .filter(user -> user.nome.contains(nome) ||
                        user.cognome.contains(cognome) ||
                        user.username.contains(username))
                .collect(Collectors.toList());
    }

    // Ottieni un singolo utente tramite il suo ID
    public UsersResponse getUserById(Long id) {
        return users.stream()
                .filter(user -> user.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    // Metodo per creare un oggetto UsersResponse
    private UsersResponse createUserResponse(Long id, String nome, String cognome, String username, String email, int numero, String immagineProfilo, int livello) {
        UsersResponse response = new UsersResponse();
        response.id = id;
        response.nome = nome;
        response.cognome = cognome;
        response.username = username;
        response.email = email;
        response.numero = numero;
        response.immagineProfilo = immagineProfilo;
        response.livello = livello;
        return response;
    }
}
