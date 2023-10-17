package project.accede.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sport_match")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SportMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "date")
    private Calendar date;
    @Column(name = "num_of_players")
    private Integer num_of_players;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "match_players",
            joinColumns = @JoinColumn(name = "sport_match_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> players = new HashSet<>();
}
