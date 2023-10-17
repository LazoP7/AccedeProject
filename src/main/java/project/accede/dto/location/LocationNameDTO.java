package project.accede.dto.location;

import lombok.Data;

@Data
public class LocationNameDTO {

    private String name;

    @Override
    public String toString() {
        return "LocationNameDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
