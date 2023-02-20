package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import com.tekion.GameOfCricket.Models.Team;


public interface ScoreBoardService {
    void printScoreBoard(Team team);
    void saveStats(Team team, MatchEntity match);
    ScoreBoardEntity getRecord(ScoreBoardEntity scoreBoardEntity);
}
