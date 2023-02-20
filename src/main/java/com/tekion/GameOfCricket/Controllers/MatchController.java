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

    @PostMapping("/")
    public String start(@RequestBody MatchEntity matchEntity){
        matchService.startMatch(matchEntity);
        return "Match Completed";
    }

    @GetMapping("/")
    public MatchEntity getDetails(@RequestBody MatchEntity matchEntity){
        return matchService.getDetails(matchEntity);
    }

}
