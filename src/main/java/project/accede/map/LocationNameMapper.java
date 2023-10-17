package project.accede.map;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.accede.dto.location.LocationNameDTO;
import project.accede.entities.Location;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationNameMapper {

    LocationNameDTO locationNameToString(Location location);
}
