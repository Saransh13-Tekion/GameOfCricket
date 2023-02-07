package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;

public class PlayerService {
    public static int getRuns(Player player){
        return (int)(Math.random()*8);
    }
}
