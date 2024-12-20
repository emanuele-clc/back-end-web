package com.unical.backendweb.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    private int id;

    @NotBlank(message = "Campo vuoto")
    @NotNull(message = "Campo mancante")
    @Pattern(regexp = "[a-zA-Z]*", message = "Caratteri proibiti.")
    private String nome, cognome;

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    private String username;

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    @Size(min = 6, message = "La password deve essere lunga almeno 6 caratteri")
    private String password;

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    private String email;

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    private String numero, foto_profilo;

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    private int livello;

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    public int getId() {
        return id;
    }

    public void setId(@NotNull(message = "Campo mancante") @NotBlank(message = "Campo vuoto") int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public @NotNull(message = "Campo mancante") @NotBlank(message = "Campo vuoto") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Campo mancante") @NotBlank(message = "Campo vuoto") String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @NotNull(message = "Campo mancante") @NotBlank(message = "Campo vuoto") String getNumero() {
        return numero;
    }

    public void setNumero(@NotNull(message = "Campo mancante") @NotBlank(message = "Campo vuoto") String numero) {
        this.numero = numero;
    }

    public @NotNull(message = "Campo mancante") @NotBlank(message = "Campo vuoto") String getFoto_profilo() {
        return foto_profilo;
    }

    public void setFoto_profilo(@NotNull(message = "Campo mancante") @NotBlank(message = "Campo vuoto") String foto_profilo) {
        this.foto_profilo = foto_profilo;
    }

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    public int getLivello() {
        return livello;
    }

    public void setLivello(@NotNull(message = "Campo mancante") @NotBlank(message = "Campo vuoto") int livello) {
        this.livello = livello;
    }

    public User(int id, String nome, String cognome, String username, String password, String email, String numero, String foto_profilo, int livello) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.password = password;
        this.numero = numero;
        this.foto_profilo = foto_profilo;
        this.livello = livello;
    }
}
