package edu.eci.agronomo.farm.model.land;

import edu.eci.agronomo.farm.model.ranch.Ranch;

import java.util.List;

public record LandDto(String farmId, String name, String subPurpose, String location, String imageUrl, float area) {
}
