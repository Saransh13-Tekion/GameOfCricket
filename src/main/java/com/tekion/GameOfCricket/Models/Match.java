package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Services.MatchService;

public class Match {

    private Team firstTeam, secondTeam;
    private int totalOvers = 0;

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }

}
