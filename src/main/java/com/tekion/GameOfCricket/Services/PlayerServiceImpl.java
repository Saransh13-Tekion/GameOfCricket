package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Exception.*;
import com.tekion.GameOfCricket.Models.*;
import com.tekion.GameOfCricket.Repository.PlayerMongoRepository;
import com.tekion.GameOfCricket.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Long;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMongoRepository playerMongoRepository;

    @Override
    public void addPlayer(List<PlayerEntity> players){
        for(PlayerEntity player:players) {
            player.setCreatedAt(LocalDateTime.now());
            playerRepository.save(player);
           // playerMongoRepository.save(new PlayerDocument(1L));
        }
    }

    @Override
    public PlayerEntity getPlayer(Long id){
        return playerRepository.findById(id).orElse(null);
    }


    @Override
    public void setPlayers(Team firstTeam,Team secondTeam) throws ValidationException {
        List<PlayerEntity> players = (List<PlayerEntity>) playerRepository.findAll();
        for (PlayerEntity playerEntity : players) {
            Player player = Player.builder()
                    .role((playerEntity.getRole() == "Bowler") ? PlayerRole.BOWLER:PlayerRole.BATSMAN)
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
            throw new ValidationException("Unequal number of Players in both teams") ;
        }
    }

    @Override
    public void saveStats(Team team) throws MissingDataException {
        for(Player player:team.getPlayers()){
            PlayerEntity playerEntity = playerRepository.findById(player.getId()).orElseThrow(() -> new MissingDataException("Required team not Found in Database"));
            if(playerEntity != null){
                playerEntity.setRuns(playerEntity.getRuns()+ player.getRuns());
                playerEntity.setBallsPlayed(playerEntity.getBallsPlayed() + player.getBallsPlayed());
                playerEntity.setWicketsTaken(playerEntity.getWicketsTaken()+player.getWicketsTaken());
                playerEntity.setUpdatedAt(LocalDateTime.now());
                playerRepository.save(playerEntity);
            }
        }
    }

}