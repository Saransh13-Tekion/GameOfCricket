package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.PlayerDTO;
import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.Document.PlayerDocument;
import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Exception.*;
import com.tekion.GameOfCricket.SQLRepository.PlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Long;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;
    static Logger log = LogManager.getLogger(MatchServiceImpl.class);

    @Override
    public void addPlayer(List<PlayerEntity> players){
        for(PlayerEntity player:players) {
            player.setCreatedAt(LocalDateTime.now());
            playerRepository.save(player);
        }
    }

    @Override
    public PlayerEntity getPlayer(Long id){
        return playerRepository.findById(id).orElse(null);
    }


    @Override
    public void setPlayers(TeamDTO firstTeam, TeamDTO secondTeam) throws ValidationException {
        log.info("Setting Players for teams - " + firstTeam.getName() + "," + secondTeam.getName());
        List<PlayerEntity> players = (List<PlayerEntity>) playerRepository.findAll();
        for (PlayerEntity playerEntity : players) {
            PlayerDTO player = PlayerDTO.builder()
                    .role((PlayerRole.BOWLER.getPlayerRole().equals(playerEntity.getRole())) ? PlayerRole.BOWLER:PlayerRole.BATSMAN)
                    .teamID(playerEntity.getTeamID())
                    .name(playerEntity.getName())
                    .id(playerEntity.getId())
                    .build();
            if (player.getTeamID() == firstTeam.getTeamID()) {
                firstTeam.getPlayers().add(player);
            } else {
                secondTeam.getPlayers().add(player);
            }
        }
        if(firstTeam.getPlayers().size() != secondTeam.getPlayers().size()){
            log.error("Unequal number of Players in both teams");
            throw new ValidationException("Unequal number of Players in both teams") ;
        }
    }

    @Override
    public void saveStats(TeamDTO team) throws MissingDataException {
        log.info("Saving Stats of team " + team.getName());
        for(PlayerDTO player:team.getPlayers()) {
            PlayerEntity playerEntity = playerRepository.findById(player.getId()).orElseThrow(() -> new MissingDataException("Required team not Found in Database"));
            playerEntity.setRuns(playerEntity.getRuns() + player.getRuns());
            playerEntity.setBallsPlayed(playerEntity.getBallsPlayed() + player.getBallsPlayed());
            playerEntity.setWicketsTaken(playerEntity.getWicketsTaken() + player.getWicketsTaken());
            playerEntity.setUpdatedAt(LocalDateTime.now());
            playerRepository.save(playerEntity);
        }
    }

}