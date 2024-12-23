package com.unical.backendweb.dao;

import com.unical.backendweb.model.PrenotazioneResponse;
import com.unical.backendweb.model.RequestResponse;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneDAO {
    List<PrenotazioneResponse> findAll();
    RequestResponse rimuoviPrenotazione(int id_campo, LocalDate data, int time);
}
