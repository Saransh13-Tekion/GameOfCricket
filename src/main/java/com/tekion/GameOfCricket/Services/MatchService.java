package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MatchService {
    void startMatch(MatchEntity matchEntity);
    int toss();
    Player changeBowler(Player currentBowler, ArrayList<Player> allBowlers);
    void play(Team battingTeam,boolean isFirstInnings, Team bowlingTeam);
}
