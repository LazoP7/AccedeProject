package project.accede.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.accede.dto.match.MatchDTO;
import project.accede.dto.user.query.UserDTO;
import project.accede.entities.Location;
import project.accede.services.MatchService;
import java.util.ArrayList;
import java.util.Calendar;

@CrossOrigin
@RestController
@RequestMapping(value = "match")
public class MatchController {
    @Autowired
    MatchService matchService;

    @PutMapping("sync")
    void syncMatches(){
        matchService.syncMatches();
    }

    @GetMapping("allMatches")
    ResponseEntity<ArrayList<MatchDTO>> getAllMatches(){
        ArrayList<MatchDTO> matchesDTO = matchService.getAllMatches();
        if(matchesDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchesDTO, HttpStatus.OK);
    }
    @GetMapping("location")
    ResponseEntity<ArrayList<MatchDTO>> getMatchesByLocation(@RequestParam String locationName){
        ArrayList<MatchDTO> matchDTO = matchService.getMatchesByLocation(locationName);
        if(matchDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchDTO, HttpStatus.OK);
    }

    @PostMapping
    void newMatches(@RequestParam String locationName){
        matchService.insertMatches(locationName);
    }

    @GetMapping("locationANDdate")
    ResponseEntity<ArrayList<MatchDTO>> getMatchesByLocationAndDate(@RequestParam String locationName, int month, int day){
        ArrayList<MatchDTO> matchDTO = matchService.getMatchesByLocationAndDate(locationName, month, day);
        if(matchDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchDTO,HttpStatus.OK);
    }

    @GetMapping("date")
    ResponseEntity<ArrayList<MatchDTO>> getMatchesByDate(@RequestParam int year,int month, int day){
        ArrayList<MatchDTO> matchesDTO = matchService.getMatchesByDate(year, month, day);
        if(matchesDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchesDTO,HttpStatus.OK);
    }

    @GetMapping("time")
    ResponseEntity<MatchDTO> getMatchesByTime(@RequestParam String locationName, String stringDate){
        MatchDTO matchDTO = matchService.getMatchesByDateAndTime(locationName, stringDate);
        if(matchDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchDTO,HttpStatus.OK);
    }

    @PutMapping("player")
    public void setPlayer(@RequestParam int userId, String locationName, String date){
        matchService.setPlayer(userId, locationName, date);
    }


    @GetMapping("myMatches")
    public ArrayList<MatchDTO> myMatches(@RequestParam String username){
        return matchService.myMatches(username);
    }
}
