package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Repository.PlayerRepository;
import com.tekion.GameOfCricket.Repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Long;

import java.util.List;
import java.util.Optional;

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
        if(player.getRole() == PlayerRole.BATSMAN){
            int[] runs = {0,0,0,0,0,0,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,3,3,4,4,4,4,4,6,6,6,7,7};
            return runs[(int)(Math.random()*runs.length)];
        }
        else{
            int[] runs = {0,0,0,0,0,0,0,0,1,1,1,2,2,3,4,6,7,7,7,7};
            return runs[(int)(Math.random()*runs.length)];
        }
    }
}
