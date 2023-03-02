package edu.eci.agronomo.farm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LandNotFoundException extends ResponseStatusException {

    public LandNotFoundException(String id){super(HttpStatus.NOT_FOUND, "land with ID: " + id + " not found");}
}
