package com.unical.backendweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersResponse {
    public Long id;
    public String nome;
    public String cognome;
    public String username;
    public String email;
    public int numero;
    public String immagineProfilo;
    public int livello;
}
