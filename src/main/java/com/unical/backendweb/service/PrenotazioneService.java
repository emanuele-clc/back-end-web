package com.unical.backendweb.service;

import com.unical.backendweb.dao.DBManager;
import com.unical.backendweb.model.PrenotazioneResponse;
import com.unical.backendweb.model.RequestResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    private List<PrenotazioneResponse> campi;

    // Costruttore: inizializza i campi
    public PrenotazioneService() {
        initCampi();
    }

    // Metodo per inizializzare i campi
    private void initCampi() {
        this.campi = DBManager.getInstance().getPrenotazioneDAO().findAll();
        int[] orari = {17, 18, 19, 20};

        // Itera su ogni orario
        for (int orario : orari) {
            // Per ogni campo nella lista, controlla se il time è null
            for (PrenotazioneResponse campo : campi) {
                // Verifica se il campo non è occupato e ha time == null
                if (!campo.isOccupied && campo.time == 0) {
                    // Controlla se c'è già una prenotazione con lo stesso id e lo stesso orario
                    boolean isTimeAssigned = false;
                    for (PrenotazioneResponse existingCampo : campi) {
                        // Se c'è un campo con lo stesso id e lo stesso orario, segna come assegnato
                        if (existingCampo.id == campo.id && existingCampo.time == orario) {
                            isTimeAssigned = true;
                            break;
                        }
                    }
                    // Se il time non è stato assegnato a questo campo, assegnalo
                    if (!isTimeAssigned) {
                        campo.time = orario;
                    }
                }
            }
        }
        campi.removeIf(campo -> campo.time == 0); //toglie le prenotazioni in più, con time nullo, ovvero == 0
    }

    // Metodo per ottenere tutti i campi
    public List<PrenotazioneResponse> getAllCampi() {
        initCampi();
        return campi;
    }

    // Metodo per ottenere i campi per ID (modificato per restituire tutti i campi con lo stesso ID)
    public List<PrenotazioneResponse> getCampoById(int id) {
        initCampi();
        return getAllCampi().stream()
                .filter(campo -> campo.id == id)
                .collect(Collectors.toList());
    }

    public RequestResponse rimuoviPrenotazione(int id_campo, LocalDate data, int time){
        return DBManager.getInstance().getPrenotazioneDAO().rimuoviPrenotazione(id_campo, data, time);
    }

//    // Metodo per prenotare un campo (assegnando i giocatori)
//    public PrenotazioneResponse prenotaCampo(int id_campo, PrenotazioneResponse campoRequest) {
//        PrenotazioneResponse campo = getCampoById(id_campo).stream().findFirst().orElse(null);
//        if (campo != null && !campo.isOccupied) {
//            // Se il campo non è occupato, aggiorniamo i giocatori e lo stato di occupazione
//            campo.idGiocatore1 = campoRequest.idGiocatore1;
//            campo.idGiocatore2 = campoRequest.idGiocatore2;
//            campo.idMaestro = campoRequest.idMaestro;
//            campo.isOccupied = true;  // Imposta il campo come occupato
//        }
//        return campo;  // Restituisce il campo prenotato o null se non trovato
//    }
//
}
