package edu.eci.agronomo.farm.service.ranch;

import edu.eci.agronomo.farm.model.ranch.Ranch;
import edu.eci.agronomo.farm.model.ranch.RanchDto;

import java.util.List;
import java.util.Optional;

public interface RanchService {
    Ranch create(Ranch ranch);

    Optional<Ranch> getById(String id);

    Optional<List<Ranch>> getByLandId(String landId);

    List<Ranch> getAll();

    void deleteById(String id);

    Ranch update(String ranchId, RanchDto ranch);
}
