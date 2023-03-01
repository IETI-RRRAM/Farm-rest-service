package edu.eci.agronomo.farm.model.land;

import edu.eci.agronomo.farm.model.ranch.Ranch;
import lombok.Getter;

import java.util.List;

@Getter
public record LandDto(String name, String subPurpose, String location, List<Ranch> ranches, float area) {
}
