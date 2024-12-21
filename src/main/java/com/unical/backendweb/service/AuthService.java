package com.unical.backendweb.service;

import com.unical.backendweb.dao.UserDAOImpl;
import org.springframework.stereotype.Service;

import com.unical.backendweb.dao.DBManager;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class AuthService {

    private UserDAOImpl userDAO;

    // Costruttore che inizializza il DAO e la connessione al database
    public AuthService() throws SQLException {
        Connection connection = DBManager.getConnection();
        this.userDAO = new UserDAOImpl(connection);
    }

//    public String register(User user) throws SQLException {
//        // Verifica se l'utente esiste già
//        if (userDAO.findByEmail(user.getEmail()) != null) {
//            throw new UserAlreadyExists("L'utente è già registrato");
//        }
//
//        // Aggiungi l'utente al database
//        userDAO.save(user);
//
//        return "Registrazione completata con successo";
//    }
}
