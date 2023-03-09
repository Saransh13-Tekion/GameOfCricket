package com.tekion.GameOfCricket.DTO;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import lombok.Data;


@Data
public class MatchDTO {
    private TeamDTO firstTeam, secondTeam;
    private int totalOvers;
    private Long winner;

    public void setFirstTeam(TeamEntity team) {
        this.firstTeam = new TeamDTO(team);
    }

    public void setSecondTeam(TeamEntity team) {
        this.secondTeam = new TeamDTO(team);
    }

}
