package com.unical.backendweb.service;

import com.unical.backendweb.model.CampoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

            // Stato di occupazione (alternanza tra true e false)
            boolean isOccupied = (i % 2 == 0);

            // Crea un nuovo CampoResponse senza usare il costruttore
            CampoResponse campo = new CampoResponse();
            campo.id = i + 1;
            campo.name = names[i];
            campo.image = imagePath;
            campo.isOccupied = isOccupied;

            // Aggiungi il campo alla lista
            campi.add(campo);
        }
    }

    // Metodo per ottenere tutti i campi
    public List<CampoResponse> getAllCampi() {
        return campi;
    }

    // Metodo per ottenere un campo per ID
    public CampoResponse getCampoById(int id) {
        return campi.stream()
                .filter(campo -> campo.id == id)
                .findFirst()
                .orElse(null);  // Restituisce null se il campo non esiste
    }

    // Metodo per aggiornare lo stato di occupazione di un campo
    public CampoResponse updateCampoOccupato(int id, boolean isOccupied) {
        CampoResponse campo = getCampoById(id);
        if (campo != null) {
            campo.isOccupied = isOccupied;  // Aggiorna lo stato di occupazione
        }
        return campo; // Restituisce il campo aggiornato o null se non esiste
    }

    // Metodo per prenotare un campo (assegnando i giocatori)
    public CampoResponse prenotaCampo(int id, CampoResponse campoRequest) {
        CampoResponse campo = getCampoById(id);
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
