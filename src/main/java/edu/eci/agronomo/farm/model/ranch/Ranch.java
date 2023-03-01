package edu.eci.agronomo.farm.model.ranch;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "ranch-collection")
public class Ranch {
    private static final long serialVersionUID = 1L;
    @Id
    private final String id;
    private String name;
    private String subPurpose;
    private String location;
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
