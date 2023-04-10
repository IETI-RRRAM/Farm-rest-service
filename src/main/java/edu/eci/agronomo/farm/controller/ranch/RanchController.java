package edu.eci.agronomo.farm.controller.ranch;

import edu.eci.agronomo.farm.exception.RanchNotFoundException;
import edu.eci.agronomo.farm.model.ranch.Ranch;
import edu.eci.agronomo.farm.model.ranch.RanchDto;
import edu.eci.agronomo.farm.response.RanchResponse;
import edu.eci.agronomo.farm.service.ranch.RanchService;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ranch")
public class RanchController {

    private final RanchService ranchService;

    @Autowired
    public RanchController(RanchService ranchService) {
        this.ranchService = ranchService;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RanchResponse>> getAll() {
        List<Ranch> ranches = ranchService.getAll();
        List<RanchResponse> ranchResponses = new ArrayList<>();
        ranches.forEach(ranch -> {
            ranchResponses.add(new RanchResponse(ranch));
        });
        return ResponseEntity.ok(ranchResponses);
    }

    @GetMapping(path = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RanchResponse> getById(@PathVariable String id) {
        Optional<Ranch> optionalRanch = ranchService.getById(id);
        if (optionalRanch.isPresent()) {
            Ranch ranch = optionalRanch.get();
            return ResponseEntity.ok(new RanchResponse(ranch));
        }
        else throw new RanchNotFoundException(id);
    }

    @GetMapping(path = "/own/{landId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RanchResponse>> getByLandId(@PathVariable String landId) {
        Optional<List<Ranch>> optionalRanch = ranchService.getByLandId(landId);
        if (optionalRanch.isPresent()) {
            List<Ranch> ranches = optionalRanch.get();
            List<RanchResponse> ranchResponses = new ArrayStack<RanchResponse>();
            ranches.forEach(ranch -> {
                ranchResponses.add(new RanchResponse(ranch));
            });
            return ResponseEntity.ok(ranchResponses);
        }
        else throw new RanchNotFoundException(landId);
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RanchResponse> create(@RequestBody Ranch ranch) {
        Ranch newRanch = ranchService.create(ranch);
        URI createdRanchUri = URI.create("");
        return ResponseEntity.created(createdRanchUri).body(new RanchResponse(newRanch));
    }

    @PutMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RanchResponse> update(@PathVariable String id, @RequestBody RanchDto ranch) {
        Optional<Ranch> optionalRanch = ranchService.getById(id);
        if (optionalRanch.isPresent()) {
            ranchService.create(optionalRanch.get());
            Ranch updatedRanch = ranchService.update(id, ranch);
            return ResponseEntity.ok(new RanchResponse(updatedRanch));
        }
        else throw new RanchNotFoundException(id);
    }

    @DeleteMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Ranch> optionalRanch = ranchService.getById(id);
        if (optionalRanch.isPresent()) {
            ranchService.create(optionalRanch.get());
            ranchService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else throw new RanchNotFoundException(id);
    }
}

