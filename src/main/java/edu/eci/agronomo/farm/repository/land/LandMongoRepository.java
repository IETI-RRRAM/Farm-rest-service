package edu.eci.agronomo.farm.repository.land;

import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.land.Land;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LandMongoRepository extends MongoRepository<Land, String> {

    @Query("{'farmId' : ?0 }")
    Optional<List<Land>> findByFarmId(String farmId);
}
