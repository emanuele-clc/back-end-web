package com.unical.backendweb.service;

import com.unical.backendweb.dao.DBManager;
import com.unical.backendweb.model.RequestResponse;
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
        this.users = DBManager.getInstance().getUSerDao().findAll();
    }

    // Ottieni tutti gli utenti
    public List<UsersResponse> getAllUsers() {
        this.users = DBManager.getInstance().getUSerDao().findAll();
        return users;
    }

    public RequestResponse banUser(int id) {
        return DBManager.getInstance().getUSerDao().banUser(id);
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
    public UsersResponse getUserById(int id) {
        return users.stream()
                .filter(user -> user.id==id)
                .findFirst()
                .orElse(null);
    }


//    // Metodo per creare un oggetto UsersResponse
//    private UsersResponse createUserResponse(int id, String nome, String cognome, String username, String email, String numero, String immagineProfilo, int livello) {
//        UsersResponse response = new UsersResponse();
//        response.id = id;
//        response.nome = nome;
//        response.cognome = cognome;
//        response.username = username;
//        response.email = email;
//        response.numero = numero;
//        response.immagineProfilo = immagineProfilo;
//        response.livello = livello;
//        return response;
//    }
}
