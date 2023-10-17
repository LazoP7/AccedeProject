package project.accede.map;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import project.accede.dto.location.LocationNameDTO;
import project.accede.entities.Location;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-17T18:02:27+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class LocationNameMapperImpl implements LocationNameMapper {

    @Override
    public LocationNameDTO locationNameToString(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationNameDTO locationNameDTO = new LocationNameDTO();

        locationNameDTO.setName( location.getName() );

        return locationNameDTO;
    }
}
