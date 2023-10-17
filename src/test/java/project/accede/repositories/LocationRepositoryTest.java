package project.accede.repositories;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import project.accede.AccedeApplication;
import project.accede.entities.Location;
import project.accede.services.LocationService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccedeApplication.class)
public class LocationRepositoryTest {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger( UserRepositoryTest.class );

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationService locationService;

//    @Test
//    @Transactional
//    void createLocation(){
//        Location location = new Location();
//        location.setName("Štampar Sports Centre");
//        location.setAddress("42.446253039715536, 19.242454104910678");
//        location.setSportMatches(null);
//        locationRepository.save(location);
//        logger.info(locationRepository.getReferenceById(location.getId()).toString());
//    }
//
//    @Test
//    @Transactional
//    void getLocation(){
//        logger.info(locationRepository.getLocationByName("Štampar Sports Centre").toString());
//    }


}
