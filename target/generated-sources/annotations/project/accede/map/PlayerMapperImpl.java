package project.accede.map;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import project.accede.dto.user.query.PlayerDTO;
import project.accede.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-17T18:02:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerDTO UserToPlayerDTO(User user) {
        if ( user == null ) {
            return null;
        }

        PlayerDTO playerDTO = new PlayerDTO();

        playerDTO.setName( user.getName() );
        playerDTO.setSurname( user.getSurname() );
        playerDTO.setUsername( user.getUsername() );
        playerDTO.setReputation( user.getReputation() );

        return playerDTO;
    }
}
