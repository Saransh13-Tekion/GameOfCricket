package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Models.Team;

import java.util.List;

public interface TeamService{
    void addTeam(List<TeamEntity> team);

    void resetTeam(Team firstTeam, Team secondTeam);

    TeamEntity getTeam(Long id);
    void saveStats(MatchEntity matchEntity);
}
