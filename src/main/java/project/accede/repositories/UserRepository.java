package project.accede.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.accede.entities.Location;
import project.accede.entities.Role;
import project.accede.entities.SportMatch;
import project.accede.entities.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select user from User user")
    ArrayList<User> getAllUsers();

    @Modifying
    @Query("UPDATE User u SET u.roles = :roles WHERE u.id = :userId")
    void updateUserRoles(@Param("userId") Integer userId, @Param("roles") Set<Role> roles);

    @Query("SELECT u.mySportMatches FROM User u WHERE u.id = :userId")
    Set<SportMatch> getMatchesForUser(@Param("userId") Integer userId);


    @Query("SELECT user FROM User user JOIN FETCH user.roles AS role WHERE user.username = :username")
    User findByUsername(@Param("username") String username);

}
