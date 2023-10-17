package project.accede.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", sportMatches=" + sportMatches +
                '}';
    }


    @JsonManagedReference
    @OneToMany(mappedBy = "location" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SportMatch> sportMatches = new ArrayList<>();
}
