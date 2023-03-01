package edu.eci.agronomo.farm.model.ranch;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Ranch {
    private final String id;
    private String name;
    private String subPurpose;
    private String location;
    private List<String> animalsIds;
    private float area;

    public Ranch() {
        this.id = "";
        this.name = "";
        this.subPurpose = "";
        this.location = "";
        this.area = 0;
    }

    public Ranch(String id, String name, String subPurpose, String location, Float area) {
        this.id = id;
        this.name = name;
        this.subPurpose = subPurpose;
        this.location = location;
        this.area = area;
    }

    public void update(RanchDto ranchDto) {
        this.name = ranchDto.name();
        this.subPurpose = ranchDto.subPurpose();
        this.location = ranchDto.location();
        this.area = ranchDto.area();
    }
}
