package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Models.Team;
import org.springframework.stereotype.Service;


public interface ScoreBoardService {
    void printScoreBoard(Team team);
    void saveStats(Team team, MatchEntity match);
}
