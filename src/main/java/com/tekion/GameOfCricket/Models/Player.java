package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {

    private String name;
    private int wicketsTaken;
    private boolean gotOut;
    private int runs = 0;
    private int ballsPlayed = 0;
    private PlayerRole role;
    Player wicketTakenBy;
    List<Player> TakenWickets = new ArrayList<>();

    public Player(PlayerRole role){
        this.role = role;
    }

}
