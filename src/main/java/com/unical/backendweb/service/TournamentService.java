package com.unical.backendweb.service;

import com.unical.backendweb.model.TournamentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {

    // Lista simulata per l'esempio
    private final List<TournamentResponse> tournaments = new ArrayList<>();

    // Costruttore per aggiungere tornei di esempio
    public TournamentService() {
        // Aggiungi tornei di esempio alla lista
        TournamentResponse tournament1 = new TournamentResponse();
        tournament1.id = 1;
        tournament1.name = "Torneo Primavera";
        tournament1.date = "2024-05-01";
        tournament1.participants = 16;
        tournaments.add(tournament1);

        TournamentResponse tournament2 = new TournamentResponse();
        tournament2.id = 2;
        tournament2.name = "Torneo Estate";
        tournament2.date = "2024-06-15";
        tournament2.participants = 8;
        tournaments.add(tournament2);

        TournamentResponse tournament3 = new TournamentResponse();
        tournament3.id = 3;
        tournament3.name = "Torneo Autunno";
        tournament3.date = "2024-09-20";
        tournament3.participants = 12;
        tournaments.add(tournament3);
    }

    // Ottieni tutti i tornei
    public List<TournamentResponse> getAllTournaments() {
        return tournaments;
    }

    // Ottieni un torneo per ID
    public TournamentResponse getTournamentById(int id) {
        return tournaments.stream()
                .filter(tournament -> tournament.id == id)
                .findFirst()
                .orElse(null);  // Se non trovato, restituisce null
    }

    // Crea un nuovo torneo
    public TournamentResponse createTournament(TournamentResponse tournamentResponse) {
        tournaments.add(tournamentResponse);
        return tournamentResponse;
    }

    // Aggiorna un torneo esistente
    public TournamentResponse updateTournament(int id, TournamentResponse tournamentResponse) {
        TournamentResponse existingTournament = getTournamentById(id);
        if (existingTournament != null) {
            existingTournament.name = tournamentResponse.name;
            existingTournament.date = tournamentResponse.date;
            existingTournament.participants = tournamentResponse.participants;
            return existingTournament;
        }
        return null;
    }

    // Elimina un torneo
    public boolean deleteTournament(int id) {
        TournamentResponse tournament = getTournamentById(id);
        if (tournament != null) {
            tournaments.remove(tournament);  // Rimuove il torneo dalla lista
            return true;  // Ritorna true se il torneo è stato eliminato
        }
        return false;  // Ritorna false se il torneo non esiste
    }
}
