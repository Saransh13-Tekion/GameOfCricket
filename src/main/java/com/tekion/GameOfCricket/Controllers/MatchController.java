package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.DTO.ResponseDTO;
import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Services.MatchService;
import com.tekion.GameOfCricket.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @PostMapping("/start/{matchId}")
    public ResponseDTO start(@PathVariable Long matchId){
        matchService.startMatch(matchId);
        return new ResponseDTO(true,"none");
    }

    @PostMapping("/create")
    public ResponseDTO createMatch(@RequestBody MatchEntity matchEntity){
        matchService.createMatch(matchEntity);
        return new ResponseDTO(true,"none");
    }

    @GetMapping("/{id}")
    public MatchEntity getDetails(@PathVariable Long id){
        return matchService.getDetails(id);
    }


}
