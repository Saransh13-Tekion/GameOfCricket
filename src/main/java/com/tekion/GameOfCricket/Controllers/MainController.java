package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Repository.MatchRepository;
import com.tekion.GameOfCricket.Repository.TeamRepository;
import com.tekion.GameOfCricket.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    private MatchServiceImpl matchService;

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/startMatch")
    public String startAMatch() {
      //  PlayerService player = new PlayerServiceImpl();
     //   this.matchService = new MatchServiceImpl(20,new PlayerServiceImpl(),new Match(),new PitchServiceImpl(),new ScoreBoardServiceImpl());
      //  matchService.startMatch();
        return "Match Started";
    }

    @GetMapping("/addMatchStats")
    public String addMatchStats(){
        Match match = matchService.getCurrentMatch();
        matchRepository.save(new MatchEntity(match.getFirstTeam().getTeamID(),match.getSecondTeam().getTeamID(),match.getWinner()));
        return "Done";
    }

    @GetMapping("/addTeamStats")
    public String addTeamStats(){
        Team firstTeam = matchService.getCurrentMatch().getFirstTeam();
        teamRepository.save(new TeamEntity(firstTeam.getTeamID(),firstTeam.getName(),1,firstTeam.getName()==matchService.getCurrentMatch().getWinner()?1:0));
        Team secondTeam = matchService.getCurrentMatch().getFirstTeam();
        teamRepository.save(new TeamEntity(secondTeam.getTeamID(),secondTeam.getName(),1,secondTeam.getName()==matchService.getCurrentMatch().getWinner()?1:0));
        return "Done";
    }
}
