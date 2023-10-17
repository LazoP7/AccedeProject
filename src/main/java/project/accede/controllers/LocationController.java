package project.accede.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.accede.entities.Location;
import project.accede.services.LocationService;

@RestController
@RequestMapping(value = "location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> newLocation(@RequestBody Location location){
        locationService.newLocation(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Integer id){
        Location location = locationService.getLocation(id);
        if (location==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }
}
