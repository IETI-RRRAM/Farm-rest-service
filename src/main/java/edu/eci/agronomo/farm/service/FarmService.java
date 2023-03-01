package edu.eci.agronomo.farm.service;

import edu.eci.agronomo.farm.model.Farm;

import java.util.List;
import java.util.Optional;

public interface  FarmService {
    Farm save(Farm product);

    Optional<Farm> getById(String id);

    List<Farm> getAll();

    void deleteById(String id);

    Farm update(Farm farm, String farmId);

}
