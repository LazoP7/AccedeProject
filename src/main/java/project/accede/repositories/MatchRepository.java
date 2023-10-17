package project.accede.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.accede.entities.Location;
import project.accede.entities.SportMatch;
import project.accede.entities.User;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Repository
public interface MatchRepository extends JpaRepository<SportMatch, Integer> {


    @Query("SELECT match FROM SportMatch match")
    ArrayList<SportMatch> getAllMatches();

    @Modifying
    @Query("DELETE FROM SportMatch match WHERE match.date = :date")
    void deleteByDate(@Param("date") Calendar date);

    @Modifying
    @Query(value = "INSERT INTO sport_match (date, location_id, num_of_players) VALUES (:date, :locationId, :numOfPlayers)", nativeQuery = true)
    void createMatch(@Param("date") Calendar date, @Param("locationId") Integer locationId, @Param("numOfPlayers") int numOfPlayers);


    @Query("SELECT match FROM SportMatch match WHERE location.name = :locationName AND DATE(date) = :day")
    ArrayList<SportMatch> getByLocationAndDay(@Param("locationName") String locationName, @Param("day") Calendar day);


    @Query("SELECT match FROM SportMatch match WHERE location.name = ?1")
    ArrayList<SportMatch> findByLocation(String location);

    @Query("SELECT match FROM SportMatch match WHERE DATE(date) = :day")
    ArrayList<SportMatch> getMatchByDate(@Param("day") Calendar day);

    @Query("SELECT match FROM SportMatch match " +
            "WHERE location.name = :locationName " +
            "AND STR_TO_DATE(date, '%Y-%m-%d %H:%i:%s') = :dateTime")
    SportMatch getMatchByDateAndTime(@Param("locationName")String locationName,@Param("dateTime") String dateTime);

    @Modifying
    @Query("UPDATE SportMatch match SET match = ?1 WHERE match.id = ?2")
    void updateMatch(SportMatch newMatch, Integer matchId);

    @Query("SELECT DISTINCT sm FROM SportMatch sm JOIN sm.players p WHERE p.username = :username")
    ArrayList<SportMatch> getMyMatches(@Param("username") String username);
}
