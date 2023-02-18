package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class Match {
    private Team firstTeam, secondTeam;
    private int totalOvers;
    private String winner;

    public void setFirstTeam(TeamEntity team) {
        this.firstTeam = new Team(team);
    }

    public void setSecondTeam(TeamEntity team) {
        this.secondTeam = new Team(team);
    }

}
