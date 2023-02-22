package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Data
public class Match {
    private Team firstTeam, secondTeam;
    private int totalOvers;
    private Long winner;

    public void setFirstTeam(TeamEntity team) {
        this.firstTeam = new Team(team);
    }

    public void setSecondTeam(TeamEntity team) {
        this.secondTeam = new Team(team);
    }

}
