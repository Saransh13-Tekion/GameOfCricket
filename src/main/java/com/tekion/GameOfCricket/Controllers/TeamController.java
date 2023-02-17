package com.tekion.GameOfCricket.Controllers;


import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/add")
    public String addTeam(@RequestBody TeamEntity team){
        teamService.addTeam(team);
        return "Team Added";
    }
    @PostMapping("/get")
    public TeamEntity getTeam(@RequestBody TeamEntity team){
        return teamService.getTeam(team.getTeamID());
    }
}
