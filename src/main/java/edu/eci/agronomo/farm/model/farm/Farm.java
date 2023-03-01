package edu.eci.agronomo.farm.model.farm;

import edu.eci.agronomo.farm.model.land.Land;

import java.util.ArrayList;
import java.util.List;

public class Farm {

    private final String id;
    private String name;
    private String purpose;
    private String location;
    private List<Land> lands;

    public Farm() {
        this.id = "";
        this.name = "";
        this.purpose = "";
        this.location = "";
        this.lands = new ArrayList<Land>();
    }

    public Farm(String id, String name, String purpose, String location, List<Land> lands) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.location = location;
        this.lands = lands;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Land> getLands() {
        return lands;
    }

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void update(FarmDto farmDto){
    }
}
