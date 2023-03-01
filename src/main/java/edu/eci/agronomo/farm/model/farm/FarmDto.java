package edu.eci.agronomo.farm.model.farm;



import edu.eci.agronomo.farm.model.land.Land;

import java.util.List;

public class FarmDto {

    private final String name;
    private final String purpose;
    private final String location;
    private final List<Land> lands;

    public FarmDto(String name, String purpose, String location, List<Land> lands) {
        this.name = name;
        this.purpose = purpose;
        this.location = location;
        this.lands = lands;
    }
}
