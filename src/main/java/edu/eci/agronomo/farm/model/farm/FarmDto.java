package edu.eci.agronomo.farm.model.farm;

import edu.eci.agronomo.farm.model.land.Land;

import java.util.List;

public record FarmDto(String name, String purpose, String location, List<Land> lands, Float area) {
}
