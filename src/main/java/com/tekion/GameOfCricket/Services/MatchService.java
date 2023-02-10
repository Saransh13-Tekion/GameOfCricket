package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;

import java.util.ArrayList;

public interface MatchService {
    public void startMatch();
    public int toss();
    public Player changeBowler(Player currentBowler, ArrayList<Player> allBowlers);
}
