package com.tekion.GameOfCricket.Controllers;

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
    public String start(@PathVariable Long matchId){
        matchService.startMatch(matchId);
        return "Match Completed";
    }

    @PostMapping("/create")
    public String createMatch(@RequestBody MatchEntity matchEntity){
        matchService.createMatch(matchEntity);
        return "Match Created";
    }

    @GetMapping("/{id}")
    public MatchEntity getDetails(@PathVariable Long id){
        return matchService.getDetails(id);
    }


}
