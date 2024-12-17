package com.unical.backendweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampoResponse {
    public String image;
    public int id;
    public String name;  // Il nome del campo
    public boolean isOccupied;  // Se il campo è occupato o meno

}
