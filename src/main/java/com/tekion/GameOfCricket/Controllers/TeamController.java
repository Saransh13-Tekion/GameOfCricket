package com.tekion.GameOfCricket.Controllers;


import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/")
    public String addTeam(@RequestBody List<TeamEntity> teams){
        teamService.addTeam(teams);
        return "Team Added";
    }
    @GetMapping("/")
    public TeamEntity getTeam(@RequestBody TeamEntity team){
        return teamService.getTeam(team.getTeamID());
    }
}
