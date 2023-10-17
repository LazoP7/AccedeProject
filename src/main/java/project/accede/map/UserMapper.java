package project.accede.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import project.accede.dto.user.query.UserDTO;
import project.accede.entities.User;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User UserDTOtoUser (UserDTO userDTO);
    UserDTO UsertoUserDTO (User user);
}
