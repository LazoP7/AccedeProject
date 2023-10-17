package project.accede.map;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.accede.dto.location.LocationDTO;
import project.accede.dto.match.MatchDTO;
import project.accede.entities.Location;
import project.accede.entities.SportMatch;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-17T18:02:27+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public LocationDTO locationToLocationDTO(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setSportMatches( sportMatchListToMatchDTOList( location.getSportMatches() ) );
        locationDTO.setName( location.getName() );
        locationDTO.setAddress( location.getAddress() );

        return locationDTO;
    }

    @Override
    public Location locationDTOToLocation(LocationDTO locationDTO) {
        if ( locationDTO == null ) {
            return null;
        }

        Location location = new Location();

        location.setName( locationDTO.getName() );
        location.setAddress( locationDTO.getAddress() );
        location.setSportMatches( matchDTOListToSportMatchList( locationDTO.getSportMatches() ) );

        return location;
    }

    @Override
    public ArrayList<LocationDTO> locationsToLocationsDTO(ArrayList<Location> locations) {
        if ( locations == null ) {
            return null;
        }

        ArrayList<LocationDTO> arrayList = new ArrayList<LocationDTO>();
        for ( Location location : locations ) {
            arrayList.add( locationToLocationDTO( location ) );
        }

        return arrayList;
    }

    protected List<MatchDTO> sportMatchListToMatchDTOList(List<SportMatch> list) {
        if ( list == null ) {
            return null;
        }

        List<MatchDTO> list1 = new ArrayList<MatchDTO>( list.size() );
        for ( SportMatch sportMatch : list ) {
            list1.add( matchMapper.SportMatchtoMatchDTO( sportMatch ) );
        }

        return list1;
    }

    protected List<SportMatch> matchDTOListToSportMatchList(List<MatchDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SportMatch> list1 = new ArrayList<SportMatch>( list.size() );
        for ( MatchDTO matchDTO : list ) {
            list1.add( matchMapper.MatchDTOtoSportMatch( matchDTO ) );
        }

        return list1;
    }
}
