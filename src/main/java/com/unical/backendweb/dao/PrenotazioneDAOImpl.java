package com.unical.backendweb.dao;

import com.unical.backendweb.model.PrenotazioneResponse;
import com.unical.backendweb.model.RequestResponse;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAOImpl implements PrenotazioneDAO {

    private Connection connection;

    public PrenotazioneDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PrenotazioneResponse> findAll() {
        List<PrenotazioneResponse> prenotazioni = new ArrayList<>();
        String query = "SELECT * FROM campo, prenotazionecampo";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PrenotazioneResponse prenotazione = new PrenotazioneResponse();
                prenotazione.id = rs.getInt("id");
                prenotazione.tipoSport = rs.getString("tiposport");
                prenotazione.tipoSuperficie = rs.getString("superficie");
                prenotazione.image = "/assets/image/campo" + prenotazione.id + ".jpg";
                prenotazione.name = "Campo " + prenotazione.id;
                if(prenotazione.id == rs.getInt("id_campo") && (Integer) rs.getObject("tipoprenotazione") != null) {
                    prenotazione.isOccupied = true;
                    prenotazione.tipoPrenotazione = (Integer) rs.getObject("tipoprenotazione");
                    prenotazione.date = rs.getObject("data", LocalDate.class);
                    prenotazione.time = rs.getInt("orario");
                    switch (prenotazione.tipoPrenotazione) {
                        case 0: prenotazione.idGiocatore1 = rs.getInt("id_giocatore_1");
                        case 1: {
                            prenotazione.idGiocatore1 = rs.getInt("id_giocatore_1");
                        }
                        case 2: {
                            prenotazione.idGiocatore1 = rs.getInt("id_giocatore_1");
                            prenotazione.idGiocatore2 = rs.getInt("id_giocatore_2");
                        }
                        case 3: {
                            prenotazione.idGiocatore1 = rs.getInt("id_giocatore_1");
                            prenotazione.idMaestro = rs.getInt("id_maestro");
                        }
                    }
                    prenotazione.DayTime = rs.getTimestamp("dataprenotazione");
                }
                prenotazioni.add(prenotazione);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prenotazioni;
    }

    public RequestResponse rimuoviPrenotazione(int id_campo, LocalDate data, int time) {
        RequestResponse r = new RequestResponse();

        Date sqlDate = Date.valueOf(data);

        String query = "DELETE FROM prenotazionecampo WHERE id_campo = ? AND data = ? AND orario = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id_campo);
            stmt.setDate(2, sqlDate);
            stmt.setInt(3, time);

            int righe_modificate = stmt.executeUpdate();

            if (righe_modificate == 1) {
                r.esito = true;
                r.messaggio = "La prenotazione del " + data + " alle ore " + time + ":00 per il campo " + id_campo + " è stata cancellata";
            } else {
                r.esito = false;
                r.messaggio = "Cancellazione non eseguita.";
            }
        } catch (SQLException e) {
            r.esito = false;
            r.messaggio = e.getMessage();
        }
        return r;
    }
}
