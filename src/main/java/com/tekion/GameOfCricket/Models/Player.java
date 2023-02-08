package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Services.PlayerService;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Player {

    private String name;
    private int wicketsTaken;
    private boolean gotOut;
    private int runs = 0;
    private int ballsPlayed = 0;
    private String role;
    Player wicketTakenBy;
    ArrayList<Player> TakenWickets = new ArrayList<>();

    public Player(String role){
        this.role = role;
    }

}
