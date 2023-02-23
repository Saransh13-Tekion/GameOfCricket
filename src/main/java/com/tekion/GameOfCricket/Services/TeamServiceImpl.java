package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void addTeam(List<TeamEntity> teams){
        for(TeamEntity team : teams) {
            teamRepository.save(team);
        }
    }

    @Override
    public void resetTeam(Team firstTeam,Team secondTeam){
        firstTeam.setAllOut(false);
        firstTeam.setWickets(0);
        firstTeam.setBallsPlayed(0);
        firstTeam.setTotalRuns(0);
        firstTeam.setOversPlayed(0);
        secondTeam.setOversPlayed(0);
        secondTeam.setBallsPlayed(0);
        secondTeam.setAllOut(false);
        secondTeam.setTotalRuns(0);
        secondTeam.setWickets(0);
    }

    @Override
    public TeamEntity getTeam(Long id){return teamRepository.findById(id) .orElse(null);}

    @Override
    public void saveStats(MatchEntity matchEntity){
        TeamEntity team = teamRepository.findById(matchEntity.getFirstTeamID()).orElse(null);
        team.setTotalMatches(team.getTotalMatches()+1);
        team = teamRepository.findById(matchEntity.getSecondTeamID()).orElse(null);
        team.setTotalMatches(team.getTotalMatches()+1);
        Long winner = matchEntity.getWinner();
        team = teamRepository.findById(winner).orElse(null);
        if(team != null){
            team.setMatchesWon(team.getMatchesWon()+1);
            teamRepository.save(team);
        }

    }

}
