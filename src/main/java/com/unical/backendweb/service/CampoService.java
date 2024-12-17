package com.unical.backendweb.service;

import com.unical.backendweb.model.CampoResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampoService {

    // Lista temporanea di campi
    private List<CampoResponse> campi = new ArrayList<>();

    // Metodo per ottenere tutti i campi
    public List<CampoResponse> getAllCampi() {
        return campi;
    }

    // Metodo per ottenere un campo per ID
    public CampoResponse getCampoById(int id) {
        return campi.stream()
                .filter(campo -> campo.id == id)
                .findFirst()
                .orElse(null);  // Restituisce null se non trovato
    }

    // Metodo per aggiornare lo stato di occupazione di un campo
    public CampoResponse updateCampoOccupato(int id, boolean isOccupied) {
        CampoResponse campo = getCampoById(id);
        if (campo != null) {
            campo.isOccupied = isOccupied;
            return campo;
        }
        return null;  // Se il campo non è trovato, restituisce null
    }

    public CampoService() {
        // Creazione dei 10 campi con id e stato (occupato/non occupato)
        CampoResponse campo1 = new CampoResponse();
        campo1.id = 1;
        campo1.name = "Campo A";
        campo1.image = "/assets/image/campo1.jpg";
        campo1.isOccupied = false;

        CampoResponse campo2 = new CampoResponse();
        campo2.id = 2;
        campo2.name = "Campo B";
        campo2.image = "/assets/image/campo2.jpg";  // Corretto
        campo2.isOccupied = true;

        CampoResponse campo3 = new CampoResponse();
        campo3.id = 3;
        campo3.name = "Campo C";
        campo3.image = "/assets/image/campo3.jpg";  // Corretto
        campo3.isOccupied = false;

        CampoResponse campo4 = new CampoResponse();
        campo4.id = 4;
        campo4.name = "Campo D";
        campo4.image = "/assets/image/campo4.jpg";  // Corretto
        campo4.isOccupied = true;

        CampoResponse campo5 = new CampoResponse();
        campo5.id = 5;
        campo5.name = "Campo E";
        campo5.image = "/assets/image/campo5.jpg";  // Corretto
        campo5.isOccupied = false;

        CampoResponse campo6 = new CampoResponse();
        campo6.id = 6;
        campo6.name = "Campo F";
        campo6.image = "/assets/image/campo6.jpg";  // Corretto
        campo6.isOccupied = false;

        CampoResponse campo7 = new CampoResponse();
        campo7.id = 7;
        campo7.name = "Campo G";
        campo7.image = "/assets/image/campo7.jpg";  // Corretto
        campo7.isOccupied = true;

        CampoResponse campo8 = new CampoResponse();
        campo8.id = 8;
        campo8.name = "Campo H";
        campo8.image = "/assets/image/campo8.jpg";  // Corretto
        campo8.isOccupied = false;

        CampoResponse campo9 = new CampoResponse();
        campo9.id = 9;
        campo9.name = "Campo I";
        campo9.image = "/assets/image/campo9.jpg";  // Corretto
        campo9.isOccupied = true;

        CampoResponse campo10 = new CampoResponse();
        campo10.id = 10;
        campo10.name = "Campo J";
        campo10.image = "/assets/image/campo10.jpg";  // Corretto
        campo10.isOccupied = false;


        // Aggiunta dei campi alla lista
        campi.add(campo1);
        campi.add(campo2);
        campi.add(campo3);
        campi.add(campo4);
        campi.add(campo5);
        campi.add(campo6);
        campi.add(campo7);
        campi.add(campo8);
        campi.add(campo9);
        campi.add(campo10);
    }
}
