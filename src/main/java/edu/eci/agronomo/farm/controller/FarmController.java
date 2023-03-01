package edu.eci.agronomo.farm.controller;

import edu.eci.agronomo.farm.model.Farm;
import edu.eci.agronomo.farm.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/farm")
public class FarmController {
    @Autowired
    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping
    public List<Farm> getAll() {
        return farmService.getAll();
    }

    @GetMapping("/{id}")
    public Farm getById(@PathVariable Long id) {
        return farmService.getById(id);
    }

    @PostMapping
    public Farm create(@RequestBody Farm farm) {
        return farmService.create(farm);
    }

    @PutMapping("/{id}")
    public Farm update(@PathVariable Long id, @RequestBody Farm farm) {
        return farmService.update(id, farm);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        farmService.delete(id);
    }
}
