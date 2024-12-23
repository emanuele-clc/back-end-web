package com.unical.backendweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersResponse {
    public int id;
    public String nome;
    public String cognome;
    public String username;
    public String email;
    public String numero;
    public String immagineProfilo;
    public int livello;
    public boolean bannato;
}
