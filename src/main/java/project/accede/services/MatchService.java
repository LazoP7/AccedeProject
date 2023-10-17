package project.accede.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.accede.dto.match.MatchDTO;
import project.accede.dto.user.query.UserDTO;
import project.accede.entities.Location;
import project.accede.entities.SportMatch;
import project.accede.entities.User;
import project.accede.map.MatchMapper;
import project.accede.map.UserMapper;
import project.accede.repositories.LocationRepository;
import project.accede.repositories.MatchRepository;
import project.accede.repositories.UserRepository;
import java.util.*;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    MatchMapper matchMapper;

    @Autowired
    UserMapper userMapper;


    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MatchService.class );

    public ArrayList<MatchDTO> getMatchesByLocation(String location){
        ArrayList<SportMatch> sportMatches = matchRepository.findByLocation(location);
        ArrayList<MatchDTO> matchesDTO = matchMapper.SportMatchestoMatchesDTO(sportMatches);
        matchesDTO.sort(Comparator.comparing(MatchDTO::getDate));
        return matchesDTO;
    }

    @Transactional
    public void syncMatches() {
        ArrayList<SportMatch> sportMatches = matchRepository.getAllMatches();
        Calendar today = Calendar.getInstance();
        for (SportMatch sportMatch : sportMatches) {
            if (sportMatch.getDate().get(Calendar.DAY_OF_YEAR) < today.get(Calendar.DAY_OF_YEAR)) {
                matchRepository.deleteByDate(sportMatch.getDate());
                sportMatch.setPlayers(null);
                sportMatch.getDate().set(Calendar.DAY_OF_MONTH, sportMatch.getDate().get(Calendar.DAY_OF_MONTH) + 8);
            }
        }
        for (SportMatch sportMatch : sportMatches) {
            matchRepository.updateMatch(sportMatch, sportMatch.getId());
        }
    }
    @Transactional
    public void insertMatches(String locationName) {
        Location location = locationRepository.getLocationByName(locationName);
        List<SportMatch> sportMatches = location.getSportMatches();
        Calendar calKeeper = Calendar.getInstance();
            for (int j = 0; j <= 8; j++) {
                for (int i = 7; i <= 22; i++) {
                    Calendar rightNow = Calendar.getInstance();
                    rightNow.set(Calendar.MINUTE, 0);
                    rightNow.set(Calendar.SECOND, 0);
                    rightNow.set(Calendar.MILLISECOND, 0);
                    rightNow.set(Calendar.DAY_OF_MONTH, calKeeper.get(Calendar.DAY_OF_MONTH));
                    SportMatch sportMatch = new SportMatch();
                    sportMatch.setNum_of_players(4);
                    sportMatch.setLocation(location);
                    rightNow.set(Calendar.HOUR_OF_DAY, i);
                    sportMatch.setDate(rightNow);
                    sportMatches.add(sportMatch);
                    logger.info(sportMatch.toString());
                }
                calKeeper.add(Calendar.DAY_OF_MONTH, 1);
            }
            location.setSportMatches(sportMatches);
            locationRepository.save(location);
    }

    public ArrayList<MatchDTO> getMatchesByLocationAndDate(String locationName, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.MONTH, month-1);
        date.set(Calendar.DAY_OF_MONTH, day);
        ArrayList<SportMatch> sportMatches = matchRepository.getByLocationAndDay(locationName, date);
        for(SportMatch sportMatch : sportMatches){
            logger.info(sportMatch.toString());
        }
        return matchMapper.SportMatchestoMatchesDTO(sportMatches);
    }

    public ArrayList<MatchDTO> getMatchesByDate(int year, int month, int day){
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH,month-1);
        date.set(Calendar.DAY_OF_MONTH, day);
        ArrayList<SportMatch> sportMatches = matchRepository.getMatchByDate(date);
        return matchMapper.SportMatchestoMatchesDTO(sportMatches);
    }

    public MatchDTO getMatchesByDateAndTime(String locationName , String stringDate) {
        SportMatch sportMatch = matchRepository.getMatchByDateAndTime(locationName, stringDate);
        return matchMapper.SportMatchtoMatchDTO(sportMatch);
    }

    @Transactional
    public void setPlayer(int userId, String locationName, String stringDate) {
        SportMatch match = matchRepository.getMatchByDateAndTime(locationName, stringDate);
        User user = userRepository.getReferenceById(userId);
        Set <User> players = match.getPlayers();
        Set <SportMatch> userMatches = userRepository.getMatchesForUser(userId);
        players.add(user);
        userMatches.add(match);
        matchRepository.updateMatch(match, match.getId());
        userRepository.save(user);
        SportMatch newMatch = matchRepository.getReferenceById(match.getId());
        for(User player : newMatch.getPlayers()) {
            UserDTO player2 = userMapper.UsertoUserDTO(player);
            logger.info(player2.toString());
            logger.info(match.getId().toString());
        }
    }
    public ArrayList<MatchDTO> getAllMatches() {
        ArrayList<SportMatch> sportMatches = matchRepository.getAllMatches();
        return matchMapper.SportMatchestoMatchesDTO(sportMatches);

    }

    public ArrayList<MatchDTO> myMatches(String username) {
        ArrayList<SportMatch> sportMatches = matchRepository.getMyMatches(username);
        return matchMapper.SportMatchestoMatchesDTO(sportMatches);
    }
}