package edu.eci.agronomo.farm.model.farm;

import edu.eci.agronomo.farm.model.land.Land;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Farm {

    private final String id;
    private String name;
    private String purpose;
    private String location;
    private List<Land> lands;
    private float area;

    public Farm() {
        this.id = "";
        this.name = "";
        this.purpose = "";
        this.location = "";
        this.lands = new ArrayList<Land>();
        this.area = 0;
    }

    public Farm(String id, String name, String purpose, String location, List<Land> lands, Float area) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.location = location;
        this.lands = lands;
        this.area = area;
    }

    public void update(FarmDto farmDto){
        this.name = farmDto.name();
        this.purpose = farmDto.purpose();
        this.location = farmDto.location();
        this.lands = farmDto.lands();
        this.area = farmDto.area();
    }
}
