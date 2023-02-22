package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Services.runGenerator.RunGeneratorFactory;

import java.util.List;

public interface PlayerService {
    void addPlayer(List<PlayerEntity> players);
    PlayerEntity getPlayer(Long id);
    void setPlayers(Team firstTeam, Team secondTeam);
    void saveStats(Team team);
    void resetPlayers(Team team);
}
