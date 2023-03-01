package edu.eci.agronomo.farm.respository.land;

import edu.eci.agronomo.farm.model.land.Land;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandMongoRepository extends MongoRepository<Land, String> {
}
