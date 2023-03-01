package edu.eci.agronomo.farm.model.farm;

import edu.eci.agronomo.farm.model.land.Land;
import lombok.Getter;

import java.util.List;

@Getter
public record FarmDto(String name, String purpose, String location, List<Land> lands, Float area) {
}
