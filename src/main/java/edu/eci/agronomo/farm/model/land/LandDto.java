package edu.eci.agronomo.farm.model.land;

import edu.eci.agronomo.farm.model.ranch.Ranch;

import java.util.List;

public record LandDto(String name, String subPurpose, String location, List<Ranch> ranches, float area) {
}
