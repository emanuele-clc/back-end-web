package com.unical.backendweb.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "Campo vuoto")
    @NotNull(message = "Campo mancante")
    @Pattern(regexp = "[a-zA-Z]*", message = "Caratteri proibiti.")
    private String nome, cognome;

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    private String email;

    @NotNull(message = "Campo mancante")
    @NotBlank(message = "Campo vuoto")
    @Size(min = 6, message = "La password deve essere lunga almeno 6 caratteri")
    private String password;

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

    public User(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    public User() {}
}
