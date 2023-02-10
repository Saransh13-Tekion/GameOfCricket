package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Utilities.Constants;
import com.tekion.GameOfCricket.Models.Player;

import java.util.List;

public class TeamServiceImpl implements TeamService{
    public void makeTeam(List<Player> players, int noOfBowlers){
        for(int i = 0;i< Constants.totalPlayers ;i++){
            if(i<Constants.totalPlayers - noOfBowlers) {
                players.add(new Player(PlayerRole.BATSMAN));
            }
            else{
                players.add(new Player(PlayerRole.BOWLER));
            }
            players.get(i).setName("Player" + (i+1));
        }
    }
}
