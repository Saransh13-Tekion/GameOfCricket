package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Models.Player;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    int getRuns(Player player);
}
