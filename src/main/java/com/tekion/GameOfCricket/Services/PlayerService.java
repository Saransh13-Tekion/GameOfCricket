package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.Player;

public class PlayerService {
    public int getRuns(Player player){
        if(player.getRole() == PlayerRole.BATSMAN){
            int[] runs = {0,0,1,1,1,1,2,2,2,2,3,3,4,4,6,6,7,7};
            return runs[(int)(Math.random()*runs.length)];
        }
        else{
            int[] runs = {0,0,1,1,1,2,2,3,4,6,7,7,7,7};
            return runs[(int)(Math.random()*runs.length)];
        }
    }
}
