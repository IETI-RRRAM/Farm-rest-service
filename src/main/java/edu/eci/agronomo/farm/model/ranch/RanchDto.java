package edu.eci.agronomo.farm.model.ranch;

import lombok.Getter;

import java.util.List;

@Getter
public record RanchDto(String name, String subPurpose, String location, List<String> animalsIds, float area) {

}
