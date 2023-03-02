package edu.eci.agronomo.farm.controller.ranch;

import edu.eci.agronomo.farm.exception.RanchNotFoundException;
import edu.eci.agronomo.farm.model.ranch.Ranch;
import edu.eci.agronomo.farm.model.ranch.RanchDto;
import edu.eci.agronomo.farm.service.ranch.RanchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Ranch>> getAll() {
        return ResponseEntity.ok(ranchService.getAll());
    }

    @GetMapping(path = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ranch> getById(@PathVariable String id) {
        Optional<Ranch> optionalRanch = ranchService.getById(id);
        if (optionalRanch.isPresent()) {
            Ranch ranch = optionalRanch.get();
            return ResponseEntity.ok(ranch);
        }
        else throw new RanchNotFoundException(id);
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ranch> create(@RequestBody Ranch ranch) {
        Ranch newRanch = ranchService.create(ranch);
        URI createdRanchUri = URI.create("");
        return ResponseEntity.created(createdRanchUri).body(newRanch);
    }

    @PutMapping(path = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ranch> update(@PathVariable String id, @RequestBody RanchDto ranch) {
        Optional<Ranch> optionalRanch = ranchService.getById(id);
        if (optionalRanch.isPresent()) {
            ranchService.create(optionalRanch.get());
            Ranch updatedRanch = ranchService.update(id, ranch);
            return ResponseEntity.ok(updatedRanch);
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

