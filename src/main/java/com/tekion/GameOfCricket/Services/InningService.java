package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;

import java.util.ArrayList;

public interface InningService {
    void play(Match currentMatch, Team battingTeam, boolean isFirstInnings, Team bowlingTeam) throws ValidationException;
    Player changeBowler(Player currentBowler, ArrayList<Player> allBowlers);
}
