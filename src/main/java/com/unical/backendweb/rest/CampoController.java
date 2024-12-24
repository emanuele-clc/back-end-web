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
    @GetMapping(path = "/fields/{data}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrenotazioneResponse>> getAllCampi(@PathVariable String data) {
        LocalDate LD = LocalDate.parse(data);
        List<PrenotazioneResponse> campi = campoService.getAllCampi(LD);
        return ResponseEntity.ok(campi);
    }

    // Ottieni un campo per ID
    @GetMapping(path = "/fields/{data}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrenotazioneResponse>> getCampiById( @PathVariable String data, @PathVariable int id) {
        LocalDate LD = LocalDate.parse(data);
        List<PrenotazioneResponse> campi = campoService.getCampoById(id, LD);
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

    // Prenota un campo per un giocatore (aggiorna il campo con idGiocatore1 e idGiocatore2)
    @GetMapping(path = "/fields/prenota", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestResponse prenotaCampo(@RequestParam int id,
                                        @RequestParam String date,
                                        @RequestParam int time,
                                        @RequestParam int id_a,
                                        @RequestParam(required = false) Integer id_b,
                                        @RequestParam int tipoprenotazione) {
        System.out.println(id+":"+date+":"+time+":"+id_a+":"+id_b+":"+tipoprenotazione);
        RequestResponse r = new RequestResponse();
        r.esito = true;
        r.messaggio = "Grande";
        return r;
    }

}
