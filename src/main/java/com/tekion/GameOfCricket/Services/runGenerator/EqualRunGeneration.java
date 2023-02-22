package com.tekion.GameOfCricket.Services.runGenerator;

import com.tekion.GameOfCricket.Enums.PlayerRole;

import java.util.Optional;

public class EqualRunGeneration implements GetRuns{
    @Override
    public int generateRuns(PlayerRole playerRole) {
        int[] runs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 4, 6, 7, 7, 7, 7};
        return runs[(int)(Math.random()*runs.length)];
    }
}
