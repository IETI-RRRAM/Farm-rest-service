package edu.eci.agronomo.farm.service;

import edu.eci.agronomo.farm.model.Farm;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class FarmServiceImpl implements FarmService {

    private final HashMap<String, Farm> memory = new HashMap<>();

    @Override
    public Farm save(Farm product) {
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
    public List<Farm> all() {
        return (List<Farm>) memory;
    }

    @Override
    public void deleteById(String id) {
        memory.remove(id);
    }

    @Override
    public Farm update(FarmDto farmDto, String id) {
        Farm updatedProduct =  memory.get(id);
        updatedProduct.update(productDto);
        return updatedProduct;
    }
}
