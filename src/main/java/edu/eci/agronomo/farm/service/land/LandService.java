package edu.eci.agronomo.farm.service.land;

import edu.eci.agronomo.farm.model.land.Land;
import edu.eci.agronomo.farm.model.land.LandDto;

import java.util.List;
import java.util.Optional;

public interface LandService {
    Land create(Land land);

    Optional<Land> getById(String id);

    Optional<List<Land>> getByFarmId(String farmId);

    List<Land> getAll();

    void deleteById(String id);

    Land update(String landId, LandDto land);
}
