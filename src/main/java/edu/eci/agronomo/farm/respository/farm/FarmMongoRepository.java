package edu.eci.agronomo.farm.respository.farm;

import edu.eci.agronomo.farm.model.farm.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmMongoRepository extends MongoRepository<Farm, String> {
}
