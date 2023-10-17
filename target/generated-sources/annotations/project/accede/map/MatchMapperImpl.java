package project.accede.map;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.accede.dto.match.MatchDTO;
import project.accede.dto.user.query.PlayerDTO;
import project.accede.entities.Location;
import project.accede.entities.SportMatch;
import project.accede.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-17T18:02:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class MatchMapperImpl implements MatchMapper {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private LocationNameMapper locationNameMapper;

    @Override
    public SportMatch MatchDTOtoSportMatch(MatchDTO matchDTO) {
        if ( matchDTO == null ) {
            return null;
        }

        SportMatch sportMatch = new SportMatch();

        sportMatch.setDate( matchDTO.getDate() );
        sportMatch.setNum_of_players( matchDTO.getNum_of_players() );
        sportMatch.setPlayers( playerDTOSetToUserSet( matchDTO.getPlayers() ) );

        return sportMatch;
    }

    @Override
    public String locationToLocationName(Location location) {
        if ( location == null ) {
            return null;
        }

        String string = new String();

        return string;
    }

    @Override
    public MatchDTO SportMatchtoMatchDTO(SportMatch sportMatch) {
        if ( sportMatch == null ) {
            return null;
        }

        MatchDTO matchDTO = new MatchDTO();

        matchDTO.setLocationName( locationNameMapper.locationNameToString( sportMatch.getLocation() ) );
        matchDTO.setDate( sportMatch.getDate() );
        matchDTO.setNum_of_players( sportMatch.getNum_of_players() );
        matchDTO.setPlayers( userSetToPlayerDTOSet( sportMatch.getPlayers() ) );

        return matchDTO;
    }

    @Override
    public ArrayList<MatchDTO> SportMatchestoMatchesDTO(ArrayList<SportMatch> sportMatches) {
        if ( sportMatches == null ) {
            return null;
        }

        ArrayList<MatchDTO> arrayList = new ArrayList<MatchDTO>();
        for ( SportMatch sportMatch : sportMatches ) {
            arrayList.add( SportMatchtoMatchDTO( sportMatch ) );
        }

        return arrayList;
    }

    protected User playerDTOToUser(PlayerDTO playerDTO) {
        if ( playerDTO == null ) {
            return null;
        }

        User user = new User();

        user.setName( playerDTO.getName() );
        user.setSurname( playerDTO.getSurname() );
        user.setUsername( playerDTO.getUsername() );
        user.setReputation( playerDTO.getReputation() );

        return user;
    }

    protected Set<User> playerDTOSetToUserSet(Set<PlayerDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<User> set1 = new LinkedHashSet<User>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PlayerDTO playerDTO : set ) {
            set1.add( playerDTOToUser( playerDTO ) );
        }

        return set1;
    }

    protected Set<PlayerDTO> userSetToPlayerDTOSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<PlayerDTO> set1 = new LinkedHashSet<PlayerDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( playerMapper.UserToPlayerDTO( user ) );
        }

        return set1;
    }
}
