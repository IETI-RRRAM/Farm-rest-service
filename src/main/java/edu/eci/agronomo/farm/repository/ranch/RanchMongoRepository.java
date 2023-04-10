package edu.eci.agronomo.farm.repository.ranch;

import edu.eci.agronomo.farm.model.land.Land;
import edu.eci.agronomo.farm.model.ranch.Ranch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RanchMongoRepository extends MongoRepository<Ranch, String> {
    @Query("{'landId' : ?0 }")
    Optional<List<Ranch>> findByLandId(String landId);
}
