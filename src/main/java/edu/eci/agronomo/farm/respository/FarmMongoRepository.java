package edu.eci.agronomo.farm.respository;

import edu.eci.agronomo.farm.model.farm.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FarmMongoRepository extends MongoRepository<Farm, String> {
}
