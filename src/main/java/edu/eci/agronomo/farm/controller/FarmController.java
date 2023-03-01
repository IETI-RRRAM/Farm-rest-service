package edu.eci.agronomo.farm.controller;

import edu.eci.agronomo.farm.exception.FarmNotFoundException;
import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.farm.FarmDto;
import edu.eci.agronomo.farm.service.FarmService;
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
        Optional<Farm> optionalProduct = farmService.getById(id);
        if (optionalProduct.isPresent()) {
            Farm product = optionalProduct.get();
            return ResponseEntity.ok(product);
        }
        else throw new FarmNotFoundException(id);
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farm> create(@RequestBody Farm farm) {
        Farm newProduct = farmService.create(farm);
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(newProduct);
    }

    @PutMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farm> update(@PathVariable String id, @RequestBody FarmDto farm) {
        Optional<Farm> optionalProduct = farmService.getById(id);
        if (optionalProduct.isPresent()) {
            farmService.create(optionalProduct.get());
            Farm updatedProduct = farmService.update(id, farm);
            return ResponseEntity.ok(updatedProduct);
        }
        else throw new FarmNotFoundException(id);
    }

    @DeleteMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Farm> optionalProduct = farmService.getById(id);
        if (optionalProduct.isPresent()) {
            farmService.create(optionalProduct.get());
            farmService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else throw new FarmNotFoundException(id);
    }
}
