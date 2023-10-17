package project.accede.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.accede.entities.Location;
import project.accede.entities.SportMatch;

import java.util.ArrayList;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {


    @Query("SELECT loc.sportMatches FROM Location loc WHERE loc.id = :location_id")
    ArrayList<SportMatch> getMatchesFromLocation(Location location);

    @Query("SELECT location FROM Location location WHERE location.name = ?1")
    Location getLocationByName(String locationName);


    @Query(value = "SELECT location FROM Location location where location.id = ?1")
    Location getLocation(Integer id);

    @Modifying
    @Query("UPDATE Location loc SET loc = ?1 WHERE loc.id = ?2")
    void updateLocation(Location location, Integer id);
}
