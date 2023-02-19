package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.*;
import com.tekion.GameOfCricket.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void addPlayer(List<PlayerEntity> players){
        for(PlayerEntity player:players) {
            playerRepository.save(player);
        }
    }

    @Override
    public PlayerEntity getPlayer(Long id){
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public int getRuns(Player player){
        int[] runs;
        if(player.getRole() == PlayerRole.BATSMAN){
            runs = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 6, 6, 6, 7, 7};
        }
        else{
            runs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 4, 6, 7, 7, 7, 7};
        }
        return runs[(int)(Math.random()*runs.length)];
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