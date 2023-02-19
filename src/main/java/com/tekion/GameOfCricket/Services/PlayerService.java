package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;

import java.util.List;

public interface PlayerService {
    int getRuns(Player player);
    void addPlayer(List<PlayerEntity> players);
    PlayerEntity getPlayer(Long id);
    void setPlayers(Team firstTeam, Team secondTeam);
    void saveStats(Team team);
    void resetPlayers(Team team);
}
