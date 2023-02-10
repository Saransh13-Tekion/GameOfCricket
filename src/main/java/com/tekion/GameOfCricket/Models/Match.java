package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Services.MatchService;
import lombok.Data;


@Data
public class Match {

    private Team firstTeam, secondTeam;
    private int totalOvers = 0;

    public Team getFirstTeam() {
        return firstTeam;
    }
}
