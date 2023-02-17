package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {


    private Long id;
    private String name;
    private int wicketsTaken;
    private boolean gotOut;
    private int runs = 0;
    private int ballsPlayed = 0;
    private int teamID;
    private PlayerRole role;
    Player wicketTakenBy;
    List<Player> TakenWickets = new ArrayList<>();

    public Player(PlayerRole role,int teamID){
        this.teamID = teamID;
        this.role = role;
    }
    public Player(){

    }

}
