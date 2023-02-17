package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public interface MatchService {
    void startMatch() throws SQLException, ClassNotFoundException;
    int toss();
    Player changeBowler(Player currentBowler, ArrayList<Player> allBowlers);
    void play(Team battingTeam,boolean isFirstInnings, Team bowlingTeam);
}
