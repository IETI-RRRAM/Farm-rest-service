package edu.eci.agronomo.farm.service;

import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.farm.FarmDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class FarmServiceImpl implements FarmService {

    private final HashMap<String, Farm> memory = new HashMap<>();

    @Override
    public Farm create(Farm product) {
        return memory.put(product.getId(), product);
    }

    @Override
    public Optional<Farm> getById(String id) {
        Farm product = memory.get(id);
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(product);
    }

    @Override
    public List<Farm> getAll() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public void deleteById(String id) {
        memory.remove(id);
    }

    @Override
    public Farm update(String id, FarmDto farm) {
        Farm updatedFarm =  memory.get(id);
        updatedFarm.update(farm);
        return updatedFarm;
    }
}
