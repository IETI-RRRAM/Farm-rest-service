package edu.eci.agronomo.farm.model.farm;



import edu.eci.agronomo.farm.model.land.Land;
import lombok.Getter;

import java.util.List;

@Getter
public class FarmDto {

    private final String name;
    private final String purpose;
    private final String location;
    private final List<Land> lands;
    private final float area;

    public FarmDto(String name, String purpose, String location, List<Land> lands, Float area) {
        this.name = name;
        this.purpose = purpose;
        this.location = location;
        this.lands = lands;
        this.area = area;
    }
}
