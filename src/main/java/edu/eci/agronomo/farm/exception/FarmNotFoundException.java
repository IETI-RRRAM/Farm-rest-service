package edu.eci.agronomo.farm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FarmNotFoundException extends ResponseStatusException {
    public FarmNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "farm with ID: " + id + " not found");
    }
}
