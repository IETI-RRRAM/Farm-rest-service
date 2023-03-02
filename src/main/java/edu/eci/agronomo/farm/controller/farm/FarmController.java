package edu.eci.agronomo.farm.controller.farm;

import edu.eci.agronomo.farm.exception.FarmNotFoundException;
import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.farm.FarmDto;
import edu.eci.agronomo.farm.service.farm.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/farm")
public class FarmController {

    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Farm>> getAll() {
        return ResponseEntity.ok(farmService.getAll());
    }

    @GetMapping(path = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farm> getById(@PathVariable String id) {
        Optional<Farm> optionalFarm = farmService.getById(id);
        if (optionalFarm.isPresent()) {
            Farm farm = optionalFarm.get();
            return ResponseEntity.ok(farm);
        }
        else throw new FarmNotFoundException(id);
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farm> create(@RequestBody Farm farm) {
        Farm newFarm = farmService.create(farm);
        URI createdFarmUri = URI.create("");
        return ResponseEntity.created(createdFarmUri).body(newFarm);
    }

    @PutMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farm> update(@PathVariable String id, @RequestBody FarmDto farm) {
        Optional<Farm> optionalFarm = farmService.getById(id);
        if (optionalFarm.isPresent()) {
            farmService.create(optionalFarm.get());
            Farm updatedFarm = farmService.update(id, farm);
            return ResponseEntity.ok(updatedFarm);
        }
        else throw new FarmNotFoundException(id);
    }

    @DeleteMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Farm> optionalFarm = farmService.getById(id);
        if (optionalFarm.isPresent()) {
            farmService.create(optionalFarm.get());
            farmService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else throw new FarmNotFoundException(id);
    }
}
