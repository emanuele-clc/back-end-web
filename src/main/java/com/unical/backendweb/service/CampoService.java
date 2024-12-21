package com.unical.backendweb.service;

import com.unical.backendweb.model.CampoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CampoService {

    private final List<CampoResponse> campi = new ArrayList<>();

    // Costruttore: inizializza i campi
    public CampoService() {
        initCampi();
    }

    // Metodo per inizializzare i campi
    private void initCampi() {
        String[] names = {"Campo A", "Campo B", "Campo C", "Campo D", "Campo E",
                "Campo F", "Campo G", "Campo H", "Campo I", "Campo J"};

        // Orari fissi per le prenotazioni (17, 18, 19, 20)
        int[] orari = {17, 18, 19, 20};

        // Oggetto Random per generare occupazione casuale
        Random random = new Random();

        for (int i = 0; i < names.length; i++) {
            // Percorso immagine dinamico
            String imagePath = "/assets/image/campo" + (i + 1) + ".jpg";

            // Creazione dei campi e prenotazioni per ogni orario
            for (int orario : orari) {
                // Crea un nuovo CampoResponse per ogni orario
                CampoResponse campo = new CampoResponse();
                campo.id = i + 1;
                campo.name = names[i];
                campo.image = imagePath;
                campo.time = orario; // Assegna l'orario
                campo.isOccupied = random.nextBoolean(); // Stato di occupazione casuale
                campo.date = 20231220; // Esempio di data, potresti sostituirla con un valore dinamico
                campo.DayTime = campo.date * 100 + campo.time; // Combina data e ora in un unico campo (es. YYYYMMDDHH)

                // Aggiungi il campo alla lista
                campi.add(campo);
            }
        }
    }

    // Metodo per ottenere tutti i campi
    public List<CampoResponse> getAllCampi() {
        return campi;
    }

    // Metodo per ottenere i campi per ID (modificato per restituire tutti i campi con lo stesso ID)
    public List<CampoResponse> getCampoById(int id) {
        return campi.stream()
                .filter(campo -> campo.id == id)  // Filtra i campi con lo stesso id
                .collect(Collectors.toList());   // Restituisce una lista di tutti i campi con lo stesso id
    }

    // Metodo per aggiornare lo stato di occupazione di un campo
    public CampoResponse updateCampoOccupato(int id, boolean isOccupied) {
        CampoResponse campo = getCampoById(id).stream().findFirst().orElse(null);
        if (campo != null) {
            campo.isOccupied = isOccupied;  // Aggiorna lo stato di occupazione
        }
        return campo; // Restituisce il campo aggiornato o null se non esiste
    }

    // Metodo per prenotare un campo (assegnando i giocatori)
    public CampoResponse prenotaCampo(int id, CampoResponse campoRequest) {
        CampoResponse campo = getCampoById(id).stream().findFirst().orElse(null);
        if (campo != null && !campo.isOccupied) {
            // Se il campo non è occupato, aggiorniamo i giocatori e lo stato di occupazione
            campo.idGiocatore1 = campoRequest.idGiocatore1;
            campo.idGiocatore2 = campoRequest.idGiocatore2;
            campo.idMaestro = campoRequest.idMaestro;
            campo.isOccupied = true;  // Imposta il campo come occupato
        }
        return campo;  // Restituisce il campo prenotato o null se non trovato
    }
}
