package edu.eci.agronomo.farm.response;

import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.farm.FarmDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmResponse {

    private String id;
    private String ownerId;
    private String name;
    private String purpose;
    private String location;
    private float area;


    public FarmResponse(Farm farm) {
        this.id = farm.getId();
        this.ownerId = farm.getOwnerId();
        this.name = farm.getName();
        this.purpose = farm.getPurpose();
        this.location = farm.getLocation();
        this.area = farm.getArea();
    }
}
