package com.tekion.GameOfCricket.Controllers;


import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Services.TeamServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @PostMapping("/addTeam")
    public String addTeam(@RequestBody TeamEntity team){
        TeamServiceImpl.addInTeamTable(team);
        return "Team Added";
    }
}
