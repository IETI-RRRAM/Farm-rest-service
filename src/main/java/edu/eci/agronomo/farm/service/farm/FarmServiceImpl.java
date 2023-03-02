package edu.eci.agronomo.farm.service.farm;

import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.farm.FarmDto;
import edu.eci.agronomo.farm.respository.farm.FarmMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmMongoRepository farmMongoRepository;

    @Autowired
    public FarmServiceImpl(FarmMongoRepository farmMongoRepository) {
        this.farmMongoRepository = farmMongoRepository;
    }

    @Override
    public Farm create(Farm farm) {
        return farmMongoRepository.save(farm);
    }

    @Override
    public Optional<Farm> getById(String id) {
        return farmMongoRepository.findById(id);
    }

    @Override
    public List<Farm> getAll() {
        return farmMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        farmMongoRepository.deleteById(id);
    }

    @Override
    public Farm update(String id, FarmDto farm) {
        Optional<Farm> farmToUpdate = farmMongoRepository.findById(id);
        Farm fr = farmToUpdate.orElseThrow();
        fr.update(farm);
        farmMongoRepository.save(fr);
        return fr;
    }
}
