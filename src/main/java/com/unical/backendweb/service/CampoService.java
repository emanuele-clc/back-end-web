package com.unical.backendweb.service;

import com.unical.backendweb.model.CampoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        for (int i = 0; i < names.length; i++) {
            // Percorso immagine dinamico
            String imagePath = "/assets/image/campo" + (i + 1) + ".jpg";

            // Aggiungi un'entità per ogni orario (17, 18, 19, 20)
            for (int hour = 17; hour <= 20; hour++) {
                CampoResponse campo = new CampoResponse();
                campo.id = i + 1;
                campo.name = names[i];
                campo.image = imagePath;
                campo.time = hour;  // Orario specifico
                campo.date = 20241220;  // Esempio di data, in formato YYYYMMDD

                // Imposta alcuni campi come occupati, e assegna ID giocatori o maestro
                if (i % 2 == 0) {
                    campo.isOccupied = true;  // Campo occupato
                    campo.idGiocatore1 = 101; // Giocatore 1
                    campo.idGiocatore2 = 102; // Giocatore 2
                    campo.idMaestro = 100 + i; // Assegna un maestro
                } else {
                    campo.isOccupied = false;  // Campo libero
                }

                // Aggiungi il campo alla lista
                campi.add(campo);
            }
        }
    }

    // Metodo per ottenere tutti i campi
    public List<CampoResponse> getAllCampi() {
        return campi;
    }

    // Metodo per ottenere un campo per ID (restituisce i campi per ogni orario)
    public List<CampoResponse> getCampoById(int id) {
        return campi.stream()
                .filter(campo -> campo.id == id)
                .collect(Collectors.toList());
    }

    // Metodo per aggiornare lo stato di occupazione di un campo
    public CampoResponse updateCampoOccupato(int id, int time, boolean isOccupied) {
        CampoResponse campo = campi.stream()
                .filter(c -> c.id == id && c.time == time)
                .findFirst()
                .orElse(null);

        if (campo != null) {
            campo.isOccupied = isOccupied;  // Aggiorna lo stato di occupazione
        }
        return campo; // Restituisce il campo aggiornato o null se non esiste
    }

    // Metodo per prenotare un campo (assegnando i giocatori)
    public CampoResponse prenotaCampo(int id, int time, CampoResponse campoRequest) {
        CampoResponse campo = campi.stream()
                .filter(c -> c.id == id && c.time == time)
                .findFirst()
                .orElse(null);

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
