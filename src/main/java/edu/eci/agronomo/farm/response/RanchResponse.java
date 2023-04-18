package edu.eci.agronomo.farm.response;

import edu.eci.agronomo.farm.model.ranch.Ranch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RanchResponse {

    private String id;
    private String landId;
    private String name;
    private String subPurpose;
    private String location;
    private String imageUrl;
    private float area;

    public RanchResponse(Ranch ranch){
        this.id = ranch.getId();
        this.landId = ranch.getLandId();
        this.name = ranch.getName();
        this.subPurpose = ranch.getSubPurpose();
        this.location = ranch.getLocation();
        this.imageUrl = ranch.getImageUrl();
        this.area = ranch.getArea();
    }
}
