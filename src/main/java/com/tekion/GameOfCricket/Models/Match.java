package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import lombok.Data;



@Data
public class Match {
    private Team firstTeam, secondTeam;
    private int totalOvers = 0;
    private String winner;

    public void setFirstTeam(TeamEntity team) {
        this.firstTeam = new Team(team);
    }

    public void setSecondTeam(TeamEntity team) {
        this.secondTeam = new Team(team);
    }

}
