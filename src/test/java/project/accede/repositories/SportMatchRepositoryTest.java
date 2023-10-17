package project.accede.repositories;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import project.accede.AccedeApplication;
import project.accede.dto.match.MatchDTO;
import project.accede.entities.Location;
import project.accede.entities.SportMatch;
import project.accede.map.MatchMapper;
import project.accede.services.MatchService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccedeApplication.class)
public class SportMatchRepositoryTest {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger( UserRepositoryTest.class );


    @Autowired
    MatchService matchService;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    MatchMapper matchMapper;


//    @Test
//    @Transactional
//    void repositoryTest(){
//        Location location = new Location();
//        location.setName("Stampar");
//        location.setAddress("321321,321321");
//        location.setSportMatches(null);
////        Location location2 = new Location();
////        location2.setName("Gimnazija");
////        location2.setAddress("2313,321321");
////        location2.setSportMatches(null);
////        locationRepository.save(location);
////        locationRepository.save(location2);
////        matchService.insertMatches(location.getName());
////        matchService.insertMatches(location2.getName());
//        Calendar date = Calendar.getInstance();
//        date.set(Calendar.DAY_OF_MONTH, 1);
//        date.set(Calendar.HOUR_OF_DAY, 10);
//        date.set(Calendar.MINUTE, 0);
//        date.set(Calendar.SECOND, 0);
//        date.set(Calendar.MILLISECOND, 0);
////        MatchDTO matchDto = matchMapper.SportMatchtoMatchDTO(matchRepository.getMatchByTime(location, date));
////        logger.info(setSportMatches.toString());
////        ArrayList<MatchDTO> setMatchesDTO = matchMapper.SportMatchestoMatchesDTO(matchRepository.getAllMatches());
//        ArrayList<MatchDTO> setMatchesDTO = matchMapper.SportMatchestoMatchesDTO(matchRepository.getMatchByDate(date));
////        ArrayList<MatchDTO> setMatchesDTO = matchService.getMatchesByLocationAndDate(location, date);
////        ArrayList<MatchDTO> setMatchesDTO = matchService.getMatchesByLocation(location);
////        setMatchesDTO.sort(Comparator.comparing(MatchDTO::getDate));
//        for(MatchDTO setmatchDTO : setMatchesDTO){
//            logger.info(setmatchDTO.toString());
//        }
////        logger.info(matchDTO.toString());
//    }

}
