package edu.eci.agronomo.farm.model.land;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.eci.agronomo.farm.model.ranch.Ranch;
import edu.eci.agronomo.farm.model.ranch.RanchDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private String imageUrl;
    private float area;

    public Land() {
        this.name = "";
        this.subPurpose = "";
        this.location = "";
        this.farmId ="";
        this.imageUrl = "";
        this.area = 0;
    }

    public Land(String farmId, String name, String subPurpose, String location, String imageUrl, Float area) {
        this.name = name;
        this.subPurpose = subPurpose;
        this.location = location;
        this.farmId = farmId;
        this.imageUrl = imageUrl;
        this.area = area;
    }

    public Land(LandDto landDto) {
        this.name = landDto.name();
        this.subPurpose = landDto.subPurpose();
        this.location = landDto.location();
        this.farmId = landDto.farmId();
        this.imageUrl = landDto.imageUrl();
        this.area = landDto.area();
    }

    public void update(LandDto landDto) {
        this.name = landDto.name();
        this.subPurpose = landDto.subPurpose();
        this.location = landDto.location();
        this.farmId = landDto.farmId();
        this.imageUrl = landDto.imageUrl();
        this.area = landDto.area();
    }
}
