package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.Constants;
import com.tekion.GameOfCricket.Models.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamService {
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
