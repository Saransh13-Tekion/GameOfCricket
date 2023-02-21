package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;

import java.util.ArrayList;

public interface InningService {
    void play(Team battingTeam, boolean isFirstInnings, Team bowlingTeam);
    Player changeBowler(Player currentBowler, ArrayList<Player> allBowlers);
}
