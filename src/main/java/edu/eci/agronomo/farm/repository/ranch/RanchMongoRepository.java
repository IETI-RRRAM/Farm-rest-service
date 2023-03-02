package edu.eci.agronomo.farm.repository.ranch;

import edu.eci.agronomo.farm.model.ranch.Ranch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RanchMongoRepository extends MongoRepository<Ranch, String> {
}
