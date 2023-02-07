package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamService {
    public static void makeTeam(List<Player> players, int noOfBowlers){
        for(int i = 0;i<11;i++){
            if(i<11-noOfBowlers) {
                players.add(new Player("Batsman"));
            }
            else{
                players.add(new Player("Bowler"));
            }
            players.get(i).setName("Player" + (i+1));
        }
    }
}
