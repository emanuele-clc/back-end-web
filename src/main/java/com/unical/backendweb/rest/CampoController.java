package com.unical.backendweb.rest;

import com.unical.backendweb.model.CampoResponse;
import com.unical.backendweb.service.CampoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200") // Permette le richieste dall'app Angular
@RestController
@RequestMapping("/api/fields")
public class CampoController {

    private final CampoService campoService;

    @Autowired
    public CampoController(CampoService campoService) {
        this.campoService = campoService;
    }

    // Metodo per ottenere tutti i campi
    @GetMapping
    public List<CampoResponse> getAllCampi() {
        return campoService.getAllCampi();
    }

    // Metodo per ottenere un campo specifico per ID
    @GetMapping("/{id}")
    public List<CampoResponse> getCampoById(@PathVariable int id) {
        return campoService.getCampoById(id);
    }
}
