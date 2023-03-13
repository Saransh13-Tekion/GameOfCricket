package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.ElasticSearchDocuments.ScoreBoardES;
import com.tekion.GameOfCricket.ESRepository.ScoreBoardESRepository;
import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import com.tekion.GameOfCricket.DTO.PlayerDTO;
import com.tekion.GameOfCricket.SQLRepository.ScoreBoardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreBoardServiceImpl implements ScoreBoardService{
    @Autowired
    private ScoreBoardRepository scoreBoardRepository;
    @Autowired
    private ScoreBoardESRepository scoreBoardESRepository;

    static Logger log = LogManager.getLogger(ScoreBoardServiceImpl.class);
    @Override
    public void saveStats(TeamDTO team, MatchEntity match){
        log.info("Saving Scoreboard stats of match " + match.getId());
        for(PlayerDTO player:team.getPlayers()){
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
            ScoreBoardES scoreBoardES = convertToES(scoreBoardEntity);
            scoreBoardESRepository.save(scoreBoardES);
        }
    }

    @Override
    public ScoreBoardES getRecord(Long matchId, Long playerId){
        log.info("Fetching Record of player " + playerId + " in match " + matchId);
        List<ScoreBoardES> records = (ArrayList<ScoreBoardES>) scoreBoardESRepository.findAll();
        for(ScoreBoardES record : records){
            if(record.getPlayerId().equals(playerId) && record.getMatchId().equals(matchId)){
                return record;
            }
        }
        return null;
    }

    private ScoreBoardES convertToES(ScoreBoardEntity scoreBoardEntity){
        ScoreBoardES scoreBoard = ScoreBoardES.builder()
                .teamId(scoreBoardEntity.getTeamId())
                .matchId(scoreBoardEntity.getId())
                .playerId(scoreBoardEntity.getPlayerId())
                .wicketsTaken(scoreBoardEntity.getWicketsTaken())
                .ballsPlayed(scoreBoardEntity.getBallsPlayed())
                .playerRole(scoreBoardEntity.getPlayerRole())
                .build();
        scoreBoard.setCreatedAt(LocalDateTime.now());
        return scoreBoard;
    }
}
