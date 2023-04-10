package edu.eci.agronomo.farm.controller.land;

import edu.eci.agronomo.farm.exception.FarmNotFoundException;
import edu.eci.agronomo.farm.exception.LandNotFoundException;
import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.land.Land;
import edu.eci.agronomo.farm.model.land.LandDto;
import edu.eci.agronomo.farm.response.LandResponse;
import edu.eci.agronomo.farm.service.land.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/land")
public class LandController {

    @Autowired
    private LandService landService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LandResponse>> getAllLands() {
        List<Land> lands = landService.getAll();
        List<LandResponse> landResponses = new ArrayList<>();
        lands.forEach(land -> {
            landResponses.add(new LandResponse(land));
        });
        return ResponseEntity.ok(landResponses);
    }

    @GetMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LandResponse> getLandById(@PathVariable String id) {
        Optional<Land> optionalLand = landService.getById(id);
        if (optionalLand.isPresent()) {
            Land land = optionalLand.get();
            return ResponseEntity.ok(new LandResponse(land));
        }
        else throw new LandNotFoundException(id);
    }

    @GetMapping(path = "/own/{farmId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LandResponse>> getByFarmId(@PathVariable String farmId) {
        Optional<List<Land>> optionalLand = landService.getByFarmId(farmId);
        if (optionalLand.isPresent()) {
            List<Land> lands = optionalLand.get();
            System.out.println(lands);
            List<LandResponse> landResponses = new ArrayList<LandResponse>();
            lands.forEach(land -> {
                landResponses.add(new LandResponse(land));
            });
            return ResponseEntity.ok(landResponses);
        }
        else throw new LandNotFoundException(farmId);
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LandResponse> create(@RequestBody Land land) {
        Land newLand = landService.create(land);
        URI createdLandUri = URI.create("");
        return ResponseEntity.created(createdLandUri).body(new LandResponse(newLand));
    }

    @PutMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LandResponse> updateLand(@PathVariable String id, @RequestBody LandDto landDto) {
        Optional<Land> optionalLand = landService.getById(id);
        if (optionalLand.isPresent()) {
            landService.create(optionalLand.get());
            Land updatedLand = landService.update(id, landDto);
            return ResponseEntity.ok(new LandResponse(updatedLand));
        }
        else throw new LandNotFoundException(id);
    }

    @DeleteMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteLand(@PathVariable String id) {
        Optional<Land> optionalLand = landService.getById(id);
        if (optionalLand.isPresent()) {
            landService.create(optionalLand.get());
            landService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else throw new LandNotFoundException(id);
    }

}