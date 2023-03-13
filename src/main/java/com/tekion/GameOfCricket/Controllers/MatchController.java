package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Exception.*;
import com.tekion.GameOfCricket.Services.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    /** Start the match of given match id.
     * @param matchId id of the match
     * @return the response
     * @throws ValidationException
     * @throws MissingDataException
     */
    @PostMapping("/start/{matchId}")
    public ResponseEntity start(@PathVariable Long matchId){
        Long winnerId;
        try {
           winnerId =  matchService.startMatch(matchId);
        }
        catch (MissingDataException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (ValidationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Id of Winning Team is " + winnerId);
    }

    /** Create the match record
     * @param matchEntity entity that need to be saved.
     * @return the response
     */
    @PostMapping("/create")
    public ResponseEntity createMatch(@Valid @RequestBody MatchEntity matchEntity){
        matchService.createMatch(matchEntity);
        return ResponseEntity.status(HttpStatus.OK).body(matchEntity);
    }

    /** Get the records of the required match
     * @param id  match id
     * @return match of given id
     */
    @GetMapping("/{id}")
    public MatchEntity getDetails(@PathVariable Long id){
        return matchService.getDetails(id);
    }


}
