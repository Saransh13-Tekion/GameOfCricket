package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Team {

    List<Player> players;
    private String name;
    @Builder.Default
    private int totalRuns = 0;
    @Builder.Default
    private int wickets = 0;
    @Builder.Default
    private int oversPlayed = 0;
    @Builder.Default
    private int ballsPlayed = 0;
    @Builder.Default
    boolean isAllOut = false;
    private Long teamID;

    public Team(TeamEntity team) {
        this.name = team.getTeamName();
        this.teamID = team.getId();
        this.players = new ArrayList<>();
    }
}
