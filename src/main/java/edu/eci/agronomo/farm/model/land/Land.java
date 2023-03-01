package edu.eci.agronomo.farm.model.land;

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
@Document(collection = "land-collection")
public class Land {
    private static final long serialVersionUID = 1L;
    @Id
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

    public void update(LandDto landDto) {
        this.name = landDto.name();
        this.subPurpose = landDto.subPurpose();
        this.location = landDto.location();
        this.ranches = landDto.ranches();
        this.area = landDto.area();
    }
}
