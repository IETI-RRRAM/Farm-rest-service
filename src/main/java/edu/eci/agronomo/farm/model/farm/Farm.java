package edu.eci.agronomo.farm.model.farm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "farm_collection")
public class Farm implements Serializable {

    @Id
    @JsonIgnore
    private String id;
    private String ownerId;
    private String name;
    private String purpose;
    private String location;
    private String imageUrl;
    private float area;

    public Farm() {
        this.ownerId="";
        this.name = "";
        this.purpose = "";
        this.location = "";
        this.imageUrl = "";
        this.area = 0;
    }

    public Farm(String ownerId, String name, String purpose, String location, String imageUrl, Float area) {
        this.ownerId = ownerId;
        this.name = name;
        this.purpose = purpose;
        this.location = location;
        this.imageUrl = imageUrl;
        this.area = area;
    }
    public Farm(FarmDto farmDto){
        this.ownerId = farmDto.ownerId();
        this.name = farmDto.name();
        this.purpose = farmDto.purpose();
        this.location = farmDto.location();
        this.imageUrl = farmDto.imageUrl();
        this.area = farmDto.area();
    }
    public void update(FarmDto farmDto){
        this.ownerId = farmDto.ownerId();
        this.name = farmDto.name();
        this.purpose = farmDto.purpose();
        this.location = farmDto.location();
        this.imageUrl = farmDto.imageUrl();
        this.area = farmDto.area();
    }
}
