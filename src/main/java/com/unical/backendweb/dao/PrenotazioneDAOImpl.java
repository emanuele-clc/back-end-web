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
    public List<PrenotazioneResponse> findAll(LocalDate data) {
        List<PrenotazioneResponse> prenotazioni = new ArrayList<>();
        String query = "SELECT * FROM campo;";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs1 = stmt.executeQuery(query);
            while (rs1.next()) {
                for (int i = 0; i < 4; i++) {
                    PrenotazioneResponse prenotazione = new PrenotazioneResponse();
                    prenotazione.id = rs1.getInt("id");
                    prenotazione.tipoSport = rs1.getString("tiposport");
                    prenotazione.tipoSuperficie = rs1.getString("superficie");
                    prenotazione.image = "/assets/image/campo" + prenotazione.id + ".jpg";
                    prenotazione.name = "Campo " + prenotazione.id;
                    prenotazioni.add(prenotazione);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        mergePrenotazioni(prenotazioni, data);
        return prenotazioni;

    }

    @Override
    public void mergePrenotazioni(List<PrenotazioneResponse> prenotazioni, LocalDate data){
        String query2 = "SELECT * FROM prenotazionecampo WHERE data = ?";
        Date dataSQL = Date.valueOf(data);
        try(PreparedStatement stmt2 = connection.prepareStatement(query2)){
            stmt2.setDate(1,dataSQL);
            ResultSet rs2 = stmt2.executeQuery();
            while(rs2.next()){
                for(PrenotazioneResponse prenotazione : prenotazioni){;
                    if(prenotazione.id == rs2.getInt("id_campo") && !prenotazione.isOccupied){
                        prenotazione.isOccupied = true;
                        prenotazione.tipoPrenotazione = (Integer) rs2.getObject("tipoprenotazione");
                        prenotazione.date = rs2.getObject("data", LocalDate.class);
                        prenotazione.time = rs2.getInt("orario");
                        System.out.println(prenotazione.id + ":" + prenotazione.time + "\n\n");
                        switch (prenotazione.tipoPrenotazione) {
                            case 0:
                                prenotazione.idGiocatore1 = rs2.getInt("id_giocatore_1");
                            case 1: {
                                prenotazione.idGiocatore1 = rs2.getInt("id_giocatore_1");
                            }
                            case 2: {
                                prenotazione.idGiocatore1 = rs2.getInt("id_giocatore_1");
                                prenotazione.idGiocatore2 = rs2.getInt("id_giocatore_2");
                            }
                            case 3: {
                                prenotazione.idGiocatore1 = rs2.getInt("id_giocatore_1");
                                prenotazione.idMaestro = rs2.getInt("id_maestro");
                            }
                        }
                        prenotazione.DayTime = rs2.getTimestamp("dataprenotazione");
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
