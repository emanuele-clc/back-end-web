package com.unical.backendweb.rest;

import com.unical.backendweb.model.TournamentResponse;
import com.unical.backendweb.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // Permette le richieste dall'app Angular
@RestController
@RequestMapping("/api/tournaments")  // Endpoint per i tornei
public class TournamentController {

    private final TournamentService tournamentService;  // Servizio per gestire i tornei
    private Logger logger = LoggerFactory.getLogger(TournamentController.class); // Logger per il controller dei tornei

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    // Ottieni tutti i tornei
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TournamentResponse>> getAllTournaments() {
        logger.info("getAllTournaments has been called");
        List<TournamentResponse> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok(tournaments);
    }

    // Ottieni un torneo per ID
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TournamentResponse> getTournamentById(@PathVariable int id) {
        logger.info("getTournamentById has been called for id: " + id);
        TournamentResponse tournament = tournamentService.getTournamentById(id);
        if (tournament != null) {
            return ResponseEntity.ok(tournament);
        } else {
            return ResponseEntity.notFound().build();  // Torneo non trovato
        }
    }
}
