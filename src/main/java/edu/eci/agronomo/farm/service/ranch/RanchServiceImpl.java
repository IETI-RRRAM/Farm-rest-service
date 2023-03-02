package edu.eci.agronomo.farm.service.ranch;

import edu.eci.agronomo.farm.model.ranch.Ranch;
import edu.eci.agronomo.farm.model.ranch.RanchDto;
import edu.eci.agronomo.farm.repository.ranch.RanchMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RanchServiceImpl implements RanchService {

    private final RanchMongoRepository ranchMongoRepository;

    @Autowired
    public RanchServiceImpl(RanchMongoRepository ranchMongoRepository) {
        this.ranchMongoRepository = ranchMongoRepository;
    }

    @Override
    public Ranch create(Ranch ranch) {
        return ranchMongoRepository.save(ranch);
    }

    @Override
    public Optional<Ranch> getById(String id) {
        return ranchMongoRepository.findById(id);
    }

    @Override
    public List<Ranch> getAll() {
        return ranchMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        ranchMongoRepository.deleteById(id);
    }

    @Override
    public Ranch update(String ranchId, RanchDto ranch) {
        Optional<Ranch> landToUpdate = ranchMongoRepository.findById(ranchId);
        Ranch la = landToUpdate.orElseThrow();
        la.update(ranch);
        ranchMongoRepository.save(la);
        return la;
    }
}
