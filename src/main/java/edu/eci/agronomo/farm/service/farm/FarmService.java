package edu.eci.agronomo.farm.service.farm;



import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.farm.FarmDto;

import java.util.List;
import java.util.Optional;

public interface  FarmService {
    Farm create(Farm farm);

    Optional<Farm> getById(String id);

    List<Farm> getAll();

    void deleteById(String id);

    Farm update(String farmId, FarmDto farm);

}
