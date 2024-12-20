package com.unical.backendweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Include solo i campi non nulli
public class CampoResponse {
    public String image;          // Immagine del campo
    public String name;           // Nome del campo
    public int id;                // ID del campo
    public boolean isOccupied;    // Indica se il campo è occupato
    public int date;              // Data (es: timestamp o formato YYYYMMDD)
    public int time;              // Orario (es: 17, 18, 19, 20)
    public int idGiocatore1;      // ID del primo giocatore
    public int idGiocatore2;      // ID del secondo giocatore
    public Integer idMaestro;     // ID del maestro (può essere null)
    public int DayTime;           // Combina data e ora per prenotazioni specifiche
}
