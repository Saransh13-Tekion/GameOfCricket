package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import com.tekion.GameOfCricket.Enums.*;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Repository.ScoreBoardRepository;
import com.tekion.GameOfCricket.Utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ScoreBoardServiceImpl implements ScoreBoardService{

    @Autowired
    private ScoreBoardRepository scoreBoardRepository;

    @Override
    public void saveStats(Team team, MatchEntity match){
        for(Player player:team.getPlayers()){
            ScoreBoardEntity scoreBoardEntity = ScoreBoardEntity.builder()
                    .teamId(team.getTeamID())
                    .matchId(match.getId())
                    .playerId(player.getId())
                    .wicketsTaken(player.getWicketsTaken())
                    .ballsPlayed(player.getBallsPlayed())
                    .playerRole(player.getRole().getPlayerRole())
                    .build();
            scoreBoardEntity.setCreatedAt(LocalDateTime.now());
            scoreBoardRepository.save(scoreBoardEntity);
        }
    }

    @Override
    public ScoreBoardEntity getRecord(Long matchId,Long playerId){
        ArrayList<ScoreBoardEntity> records = (ArrayList<ScoreBoardEntity>) scoreBoardRepository.findAll();
        for(ScoreBoardEntity record : records){
            if(record.getPlayerId().equals(playerId) && record.getMatchId().equals(matchId)){
                return record;
            }
        }
        return null;
    }
}
