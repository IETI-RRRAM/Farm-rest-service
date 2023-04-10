package edu.eci.agronomo.farm.service.land;

import edu.eci.agronomo.farm.model.land.Land;
import edu.eci.agronomo.farm.model.land.LandDto;
import edu.eci.agronomo.farm.repository.land.LandMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LandServiceImpl implements LandService {

    private final LandMongoRepository landMongoRepository;

    @Autowired
    public LandServiceImpl(LandMongoRepository landMongoRepository) {
        this.landMongoRepository = landMongoRepository;
    }

    @Override
    public Land create(Land land) {
        return landMongoRepository.save(land);
    }

    @Override
    public Optional<Land> getById(String id) {
        return landMongoRepository.findById(id);
    }

    @Override
    public Optional<List<Land>> getByFarmId(String farmId) {
        return landMongoRepository.findByFarmId(farmId);
    }

    @Override
    public List<Land> getAll() {
        return landMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        landMongoRepository.deleteById(id);
    }

    @Override
    public Land update(String landId, LandDto land) {
        Optional<Land> landToUpdate = landMongoRepository.findById(landId);
        Land la = landToUpdate.orElseThrow();
        la.update(land);
        landMongoRepository.save(la);
        return la;
    }
}
