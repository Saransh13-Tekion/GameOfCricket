package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Models.*;
import com.tekion.GameOfCricket.Repository.PlayerMongoRepository;
import com.tekion.GameOfCricket.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Long;
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
            playerRepository.save(player);
           // playerMongoRepository.save(new PlayerDocument(1L));
        }
    }

    @Override
    public PlayerEntity getPlayer(Long id){
        return playerRepository.findById(id).orElse(null);
    }


    @Override
    public void setPlayers(Team firstTeam,Team secondTeam){
        if(firstTeam.getPlayers().size() == 0) {
            List<PlayerEntity> players = (List<PlayerEntity>) playerRepository.findAll();
            for (PlayerEntity player : players) {
                if (player.getTeamID() == firstTeam.getTeamID()) {
                    firstTeam.getPlayers().add(new Player(player.getRole(), player.getTeamID(), player.getName(), player.getId()));
                } else {
                    secondTeam.getPlayers().add(new Player(player.getRole(), player.getTeamID(), player.getName(), player.getId()));
                }
            }
        }
        else{
            resetPlayers(firstTeam);
            resetPlayers(secondTeam);
        }
    }

    @Override
    public void resetPlayers(Team team){
        for(Player player:team.getPlayers()){
            player.setRuns(0);
            player.setGotOut(false);
            player.setWicketsTaken(0);
            player.setBallsPlayed(0);
        }
    }

    @Override
    public void saveStats(Team team){
        for(Player player:team.getPlayers()){
            PlayerEntity playerEntity = playerRepository.findById(player.getId()).orElse(null);
            if(playerEntity != null){
                playerEntity.setRuns(playerEntity.getRuns()+ player.getRuns());
                playerEntity.setBallsPlayed(playerEntity.getBallsPlayed() + player.getBallsPlayed());
                playerEntity.setWicketsTaken(playerEntity.getWicketsTaken()+player.getWicketsTaken());
                playerRepository.save(playerEntity);
            }

        }
    }

}