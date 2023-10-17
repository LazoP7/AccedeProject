package project.accede.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="username")
    private String username;
    @Column(name="mail")
    private String mail;
    @Column(name="password")
    private String password;
    @Column(name="reputation")
    private Integer reputation;
    @Column(name = "age")
    private Integer age;
    @Column(name="prof_descr")
    private String profDescr;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn (name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "players")
    private Set<SportMatch> mySportMatches = new HashSet<>();

}
