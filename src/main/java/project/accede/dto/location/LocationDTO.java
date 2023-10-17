package project.accede.dto.location;

import jakarta.persistence.Column;
import lombok.Data;
import project.accede.dto.match.MatchDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocationDTO {
    private String name;

    private String address;

    private List<MatchDTO> sportMatches;
}
