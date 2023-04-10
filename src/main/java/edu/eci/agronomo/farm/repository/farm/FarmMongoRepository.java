package edu.eci.agronomo.farm.repository.farm;

import edu.eci.agronomo.farm.model.farm.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmMongoRepository extends MongoRepository<Farm, String> {

    @Query("{'ownerId' : ?0 }")
    Optional<List<Farm>> findByOwnerId(String ownerId);
}
