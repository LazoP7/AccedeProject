package project.accede.map;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import project.accede.dto.user.query.UserDTO;
import project.accede.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-17T18:02:27+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User UserDTOtoUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setName( userDTO.getName() );
        user.setSurname( userDTO.getSurname() );
        user.setUsername( userDTO.getUsername() );
        user.setMail( userDTO.getMail() );
        user.setPassword( userDTO.getPassword() );
        user.setReputation( userDTO.getReputation() );
        user.setAge( userDTO.getAge() );
        user.setProfDescr( userDTO.getProfDescr() );

        return user;
    }

    @Override
    public UserDTO UsertoUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setName( user.getName() );
        userDTO.setSurname( user.getSurname() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setMail( user.getMail() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setReputation( user.getReputation() );
        userDTO.setAge( user.getAge() );
        userDTO.setProfDescr( user.getProfDescr() );

        return userDTO;
    }
}
