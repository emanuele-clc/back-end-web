package com.unical.backendweb.rest;

import com.unical.backendweb.model.PrenotazioneResponse;
import com.unical.backendweb.model.RequestResponse;
import com.unical.backendweb.service.PrenotazioneService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CampoController {

    private final PrenotazioneService campoService;

    public CampoController(PrenotazioneService campoService) {
        this.campoService = campoService;
    }

    // Ottieni tutti i campi
    @GetMapping(path = "/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrenotazioneResponse>> getAllCampi() {
        List<PrenotazioneResponse> campi = campoService.getAllCampi();
        return ResponseEntity.ok(campi);
    }

    // Ottieni un campo per ID
    @GetMapping(path = "/fields/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrenotazioneResponse>> getCampiById(@PathVariable int id) {
        List<PrenotazioneResponse> campi = campoService.getCampoById(id);
        if (!campi.isEmpty()) {
            return ResponseEntity.ok(campi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/fields/update/{id}/{date}/{orario}")
    public RequestResponse rimuoviPrenotazione(@PathVariable int id, @PathVariable String date, @PathVariable int orario) {
        LocalDate LD = LocalDate.parse(date);
        return campoService.rimuoviPrenotazione(id,LD,orario);
    }

//    // Prenota un campo per un giocatore (aggiorna il campo con idGiocatore1 e idGiocatore2)
//    @PostMapping(path = "/fields/{id}/prenota", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PrenotazioneResponse> prenotaCampo(@PathVariable int id, @RequestBody PrenotazioneResponse campoRequest) {
//        PrenotazioneResponse campoPrenotato = campoService.prenotaCampo(id, campoRequest);
//        if (campoPrenotato != null) {
//            return ResponseEntity.ok(campoPrenotato);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
