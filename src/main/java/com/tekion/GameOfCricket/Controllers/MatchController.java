package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Services.MatchService;
import com.tekion.GameOfCricket.Services.TeamService;
import com.tekion.GameOfCricket.Services.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @PostMapping("/startMatch")
    public String start(@RequestBody MatchEntity matchEntity){
        matchService.startMatch(matchEntity);
        return "Match Completed";
    }

}
