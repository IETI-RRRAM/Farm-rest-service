package edu.eci.agronomo.farm.model.ranch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "ranch_collection")
public class Ranch {
    private static final long serialVersionUID = 1L;
    @Id
    @JsonIgnore
    private String id;
    private String landId;
    private String name;
    private String subPurpose;
    private String location;
    private String imageUrl;
    private float area;

    public Ranch() {
        this.landId = "";
        this.name = "";
        this.subPurpose = "";
        this.location = "";
        this.imageUrl = "";
        this.area = 0;
    }

    public Ranch(String landId, String name, String subPurpose, String location, String imageUrl, Float area) {
        this.landId = landId;
        this.name = name;
        this.subPurpose = subPurpose;
        this.location = location;
        this.imageUrl = imageUrl;
        this.area = area;
    }

    public void update(RanchDto ranchDto) {
        this.landId = ranchDto.landId();
        this.name = ranchDto.name();
        this.subPurpose = ranchDto.subPurpose();
        this.location = ranchDto.location();
        this.imageUrl = ranchDto.imageUrl();
        this.area = ranchDto.area();
    }
}
