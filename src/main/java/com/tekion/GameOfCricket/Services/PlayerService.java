package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Models.Player;

import java.util.List;

public interface PlayerService {
    int getRuns(Player player);
    void addPlayer(List<PlayerEntity> players);
    PlayerEntity getPlayer(Long id);
}
