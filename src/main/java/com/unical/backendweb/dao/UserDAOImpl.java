package com.unical.backendweb.dao;

import com.unical.backendweb.model.RequestResponse;
import com.unical.backendweb.model.UsersResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UsersResponse> findAll() {
        List<UsersResponse> users = new ArrayList<>();
        String query = "SELECT * FROM giocatore";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                UsersResponse user = new UsersResponse();
                user.id = rs.getInt("id");
                user.nome = rs.getString("nome");
                user.cognome = rs.getString("cognome");
                user.username = rs.getString("username");
                user.email = rs.getString("email");
                user.numero = rs.getString("numero");
                user.immagineProfilo = rs.getString("immagine_profilo");
                user.livello = rs.getInt("livello");
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public RequestResponse banUser(int id){
        String query = "DELETE FROM giocatore WHERE id = ?";
        RequestResponse r = new RequestResponse();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Specifica il parametro 'id'
            int righe_modificate = stmt.executeUpdate();
            if(righe_modificate == 1){
                r.esito = true;
                r.messaggio = "L'utente " + id + " è stato bannato con successo";
            } else {
                r.esito = false;
                r.messaggio = "L'utente " + id + " non è stato bannato";
            }
        } catch (SQLException e) {
            r.esito = false;
            r.messaggio = e.getMessage();
        }
        return r;
    }

}
