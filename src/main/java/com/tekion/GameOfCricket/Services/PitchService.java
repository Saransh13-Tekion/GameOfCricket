package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import org.springframework.stereotype.Service;

@Service
public interface PitchService {
    void openers(Player striker, Player nonStriker);
    void swap();
    void nextPlayer(Player player);
}
