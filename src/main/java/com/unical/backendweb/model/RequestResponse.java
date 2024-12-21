package com.unical.backendweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestResponse {
    public boolean esito;
    public String messaggio;
}
