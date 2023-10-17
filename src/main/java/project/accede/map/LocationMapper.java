package project.accede.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import project.accede.dto.location.LocationDTO;
import project.accede.entities.Location;

import java.util.ArrayList;

@Mapper(componentModel = "spring",
        uses = MatchMapper.class,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationMapper {
    @Mapping(source = "sportMatches", target = "sportMatches")
    LocationDTO locationToLocationDTO(Location location);

    Location locationDTOToLocation(LocationDTO locationDTO);

    ArrayList<LocationDTO> locationsToLocationsDTO (ArrayList<Location> locations);
}
