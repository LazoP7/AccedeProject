package project.accede.map;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.accede.dto.user.query.PlayerDTO;
import project.accede.entities.User;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayerMapper {

    PlayerDTO UserToPlayerDTO(User user);
}
