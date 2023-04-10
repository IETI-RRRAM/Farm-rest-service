package edu.eci.agronomo.farm.response;

import edu.eci.agronomo.farm.model.land.Land;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LandResponse {

    private String id;
    private String farmId;
    private String name;
    private String subPurpose;
    private String location;
    private float area;

    public LandResponse(Land land) {
        this.id = land.getId();
        this.farmId = land.getFarmId();
        this.name = land.getName();
        this.subPurpose = land.getSubPurpose();
        this.location = land.getLocation();
        this.area =land.getArea();
    }
}
