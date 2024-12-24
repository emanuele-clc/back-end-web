package com.unical.backendweb.dao;

import com.unical.backendweb.model.PrenotazioneResponse;
import com.unical.backendweb.model.RequestResponse;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneDAO {
    List<PrenotazioneResponse> findAll(LocalDate data);
    void mergePrenotazioni(List<PrenotazioneResponse> prenotazioni, LocalDate data);
    RequestResponse rimuoviPrenotazione(int id_campo, LocalDate data, int time);

    RequestResponse prenotaCampo(int id, String date, int time, int idA, Integer idB, int tipoprenotazione);
}
