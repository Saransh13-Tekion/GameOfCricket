package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Team;
import org.springframework.stereotype.Service;


public interface ScoreBoardService {
    void printScoreBoard(Team team);
}
