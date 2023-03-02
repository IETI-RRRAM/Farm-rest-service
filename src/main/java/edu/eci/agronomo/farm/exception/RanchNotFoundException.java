package edu.eci.agronomo.farm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RanchNotFoundException extends ResponseStatusException {
    public RanchNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "ranch with ID: " + id + " not found");
    }
}