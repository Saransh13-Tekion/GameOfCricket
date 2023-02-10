package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;

public interface PitchService {
    public void openers(Player striker, Player nonStriker);
    public void swap();
    public void nextPlayer(Player player);
}
