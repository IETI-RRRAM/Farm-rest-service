package edu.eci.agronomo.farm.model.land;

import edu.eci.agronomo.farm.model.ranch.Ranch;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Land {
    private final String id;
    private String name;
    private String subPurpose;
    private String location;
    private List<Ranch> ranches;
    private float area;

    public Land() {
        this.id = "";
        this.name = "";
        this.subPurpose = "";
        this.location = "";
        this.ranches = new ArrayList<Ranch>();
        this.area = 0;
    }

    public Land(String id, String name, String subPurpose, String location, List<Ranch> ranches, Float area) {
        this.id = id;
        this.name = name;
        this.subPurpose = subPurpose;
        this.location = location;
        this.ranches = ranches;
        this.area = area;
    }
}
