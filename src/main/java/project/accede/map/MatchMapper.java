package project.accede.map;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import project.accede.dto.match.MatchDTO;
import project.accede.entities.Location;
import project.accede.entities.SportMatch;

import java.util.ArrayList;


@Mapper(componentModel = "spring",
        uses = {PlayerMapper.class, LocationNameMapper.class},
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MatchMapper {


    SportMatch MatchDTOtoSportMatch(MatchDTO matchDTO);

    String locationToLocationName(Location location);
    @Mapping(source = "location", target = "locationName")
    MatchDTO SportMatchtoMatchDTO(SportMatch sportMatch);

    ArrayList<MatchDTO> SportMatchestoMatchesDTO (ArrayList<SportMatch> sportMatches);
}
