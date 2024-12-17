package com.unical.backendweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TournamentResponse {
    public int id;
    public String name;
    public String date;
    public int participants;
}
