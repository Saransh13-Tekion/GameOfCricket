package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Repository.PlayerRepository;
import com.tekion.GameOfCricket.Services.TeamServiceImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Data
@NoArgsConstructor
public class Team {

    List<Player> players;
    private String name;
    private int totalRuns = 0;
    private int wickets = 0;
    private int oversPlayed = 0;
    private int ballsPlayed = 0;
    boolean isAllOut = false;
    private Long teamID;

    public Team(TeamEntity team) {
        this.name = team.getTeamName();
        this.teamID = team.getTeamID();

    }
}
