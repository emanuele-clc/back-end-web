package com.unical.backendweb.rest;

import com.unical.backendweb.model.CampoResponse;
import com.unical.backendweb.service.CampoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app
@RestController
@RequestMapping("/api")
public class CampoController {

    private final CampoService campoService;
    private Logger logger = LoggerFactory.getLogger(CampoController.class);

    public CampoController(CampoService campoService) {
        this.campoService = campoService;
    }

    // Ottieni tutti i campi
    @GetMapping(path = "/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CampoResponse>> getAllCampi() {
        logger.info("getAllCampi has been called");
        List<CampoResponse> campi = campoService.getAllCampi();
        return ResponseEntity.ok(campi);
    }

    // Ottieni un campo per ID
    @GetMapping(path = "/fields/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampoResponse> getCampoById(@PathVariable int id) {
        logger.info("getCampoById has been called for id: " + id);
        CampoResponse campo = campoService.getCampoById(id);
        if (campo != null) {
            return ResponseEntity.ok(campo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Modifica lo stato di occupazione di un campo
    @PatchMapping(path = "/fields/{id}/occupato", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampoResponse> updateCampoOccupato(@PathVariable int id, @RequestParam boolean isOccupied) {
        logger.info("updateCampoOccupato has been called for id: " + id);
        CampoResponse updatedCampo = campoService.updateCampoOccupato(id, isOccupied);
        if (updatedCampo != null) {
            return ResponseEntity.ok(updatedCampo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Prenota un campo per un giocatore (aggiorna il campo con idGiocatore1 e idGiocatore2)
    @PostMapping(path = "/fields/{id}/prenota", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampoResponse> prenotaCampo(@PathVariable int id, @RequestBody CampoResponse campoRequest) {
        logger.info("Prenota campo for id: " + id);
        CampoResponse campoPrenotato = campoService.prenotaCampo(id, campoRequest);
        if (campoPrenotato != null) {
            return ResponseEntity.ok(campoPrenotato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
