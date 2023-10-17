package project.accede.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String roleName;
    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "roles", targetEntity = User.class)
    private Set<User> user = new HashSet<>();
}
