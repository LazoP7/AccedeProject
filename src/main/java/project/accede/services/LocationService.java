package project.accede.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.accede.entities.Location;
import project.accede.repositories.LocationRepository;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;
    public void newLocation(Location location) {
        locationRepository.save(location);
    }

    public Location getLocation(Integer id) { return locationRepository.getLocation(id);
    }

//    public void newMatches(Location location) {
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.set(Calendar.MINUTE, 0);
//        rightNow.set(Calendar.SECOND, 0);
//        SportMatch match = new SportMatch();
//        match.setNumOfPlayers(4);
//        for(int i = 7; i<22; i++) {
//            rightNow.set(Calendar.HOUR_OF_DAY, i);
//            match.setDate(rightNow);
//            match.setLocation(location);
//            //locationRepository.populateLocation(location.getId(), match);
//        }
//    }
}
