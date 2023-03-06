package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;


public interface PitchService {
    void setOpeners(Player striker, Player nonStriker);
    void swap();
    void setStriker(Player player);
    Player getNonStriker();
    Player getStriker();
    void setCurrentBowler(Player currentBowler);
    Player getCurrentBowler();
}
