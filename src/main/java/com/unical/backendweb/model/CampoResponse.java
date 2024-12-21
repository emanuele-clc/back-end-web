package com.unical.backendweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampoResponse {
    public String image;
    public String name;
    public int id;
    public boolean isOccupied;  // Se il campo è occupato o meno
    public int date;            // Data in formato int (es: timestamp o formato YYYYMMDD)
    public int time;            // Orario (es: 17, 18, 19, 20)
    public int idGiocatore1;    // ID del primo giocatore
    public int idGiocatore2;    // ID del secondo giocatore
    public int idMaestro;       // ID del maestro
    public int DayTime;         // Combina data e ora per prenotazioni specifiche


}
