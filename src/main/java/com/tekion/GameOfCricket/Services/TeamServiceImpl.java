package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.SQLRepository.TeamRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;
    static Logger log = LogManager.getLogger(TeamServiceImpl.class);
    @Override
    public void addTeam(List<TeamEntity> teams){
        for(TeamEntity team : teams) {
            team.setCreatedAt(LocalDateTime.now());
            teamRepository.save(team);
        }
    }

    @Override
    public TeamEntity getTeam(Long id) throws MissingDataException {return teamRepository.findById(id).orElseThrow(()->new MissingDataException("Required team not Found in Database"));}

    @Override
    public void saveStats(MatchEntity matchEntity) throws MissingDataException {
        log.info("Saving TeamDTO stats");
        TeamEntity team = teamRepository.findById(matchEntity.getFirstTeamID()).orElseThrow(() -> new MissingDataException("Required team not Found in Database"));
        team.setTotalMatches(team.getTotalMatches() + 1);
        team = teamRepository.findById(matchEntity.getSecondTeamID()).orElseThrow(() -> new MissingDataException("Required team not Found in Database"));
        team.setTotalMatches(team.getTotalMatches() + 1);
        Long winner = matchEntity.getWinner();
        team = teamRepository.findById(winner).orElseThrow(() -> new MissingDataException("Required team not Found in Database"));
        team.setMatchesWon(team.getMatchesWon() + 1);
        team.setUpdatedAt(LocalDateTime.now());
        teamRepository.save(team);
    }

}
