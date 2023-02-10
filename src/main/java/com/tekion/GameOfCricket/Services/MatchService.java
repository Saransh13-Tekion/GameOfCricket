package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;

import java.util.ArrayList;

public interface MatchService {
    void startMatch();
    int toss();
    Player changeBowler(Player currentBowler, ArrayList<Player> allBowlers);
}
