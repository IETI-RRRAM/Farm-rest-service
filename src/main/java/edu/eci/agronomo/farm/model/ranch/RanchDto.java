package edu.eci.agronomo.farm.model.ranch;

import java.util.List;

public record RanchDto(String landId, String name, String subPurpose, String location, List<String> animalsIds, float area) {

}
