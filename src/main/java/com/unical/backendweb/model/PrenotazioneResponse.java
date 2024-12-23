package com.unical.backendweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrenotazioneResponse {
    public int id;
    public LocalDate date;
    public int time;            // Orario (es: 17, 18, 19, 20)

    public int idGiocatore1;    // ID del primo giocatore
    public int idGiocatore2;    // ID del secondo giocatore
    public int idMaestro;       // ID del maestro
    public Integer tipoPrenotazione;

    public String tipoSport;
    public String tipoSuperficie;

    public String image;
    public String name;
    public boolean isOccupied;  // Se il campo è occupato o meno
    public Timestamp DayTime;         // Combina data e ora per prenotazioni specifiche - Ha senso? io metterei "DayTimePrenotazione" = momento in cui viene fatta la richiesta
}
