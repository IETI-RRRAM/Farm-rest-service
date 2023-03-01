package edu.eci.agronomo.farm.model.ranch;

import java.util.List;

public record RanchDto(String name, String subPurpose, String location, List<String> animalsIds, float area) {

}
