package com.tekion.GameOfCricket.Services.runGenerator;

import com.tekion.GameOfCricket.Enums.PlayerRole;

public class WeightedRunGenerator implements RunGenerator {

    @Override
    public int generateRuns(PlayerRole playerRole) {
        int[] runs;
        if(PlayerRole.BATSMAN.equals(playerRole)){
            runs = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 6, 6, 6, 7, 7};
        }
        else{
            runs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 4, 6, 7, 7, 7, 7};
        }
        return runs[(int)(Math.random()*runs.length)];
    }
}
