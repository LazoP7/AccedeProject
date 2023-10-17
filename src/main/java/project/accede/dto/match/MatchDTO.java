package project.accede.dto.match;
import lombok.*;
import project.accede.dto.location.LocationNameDTO;
import project.accede.dto.user.query.PlayerDTO;
import project.accede.dto.user.query.UserDTO;
import project.accede.entities.Location;
import project.accede.entities.User;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Data

public class MatchDTO {

    @Getter
    private Calendar date;

    private Integer num_of_players;

    private LocationNameDTO locationName;

    private Set<PlayerDTO> players = new HashSet<>();

    @Override
    public String toString() {
        return "MatchDTO{" +
                "date=" + date +
                ", num_of_players=" + num_of_players +
                ", locationName='" + locationName + '\'' +
                ", players=" + players +
                '}';
    }
}
