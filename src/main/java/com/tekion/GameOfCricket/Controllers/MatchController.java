package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.DTO.ResponseDTO;
import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.Services.MatchService;
import com.tekion.GameOfCricket.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseDTO start(@PathVariable Long matchId) throws ValidationException, MissingDataException {
        matchService.startMatch(matchId);
        return new ResponseDTO(true,"none");
    }

    /** Create the match record
     * @param matchEntity entity that need to be saved.
     * @return the response
     */
    @PostMapping("/create")
    public ResponseDTO createMatch(@RequestBody MatchEntity matchEntity){
        matchService.createMatch(matchEntity);
        return new ResponseDTO(true,"none");
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
