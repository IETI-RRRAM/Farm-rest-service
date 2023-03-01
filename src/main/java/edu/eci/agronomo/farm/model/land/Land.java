package edu.eci.agronomo.farm.model.land;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.eci.agronomo.farm.model.ranch.Ranch;
import edu.eci.agronomo.farm.model.ranch.RanchDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "land_collection")
public class Land {
    private static final long serialVersionUID = 1L;
    @Id
    @JsonIgnore
    private String id;
    private String farmId;
    private String name;
    private String subPurpose;
    private String location;
    private float area;

    public Land() {
        this.id = "";
        this.name = "";
        this.subPurpose = "";
        this.location = "";
        this.farmId ="";
        this.area = 0;
    }

    public Land(String farmId, String name, String subPurpose, String location, Float area) {
        this.name = name;
        this.subPurpose = subPurpose;
        this.location = location;
        this.farmId = farmId;
        this.area = area;
    }

    public void update(LandDto landDto) {
        this.name = landDto.name();
        this.subPurpose = landDto.subPurpose();
        this.location = landDto.location();
        this.farmId = landDto.farmId();
        this.area = landDto.area();
    }
}
