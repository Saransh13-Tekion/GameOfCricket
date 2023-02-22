package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import lombok.Data;

@Data
public class Player {
    private Long id;
    private String name;
    private int wicketsTaken;
    private boolean gotOut;
    private int runs = 0;
    private int ballsPlayed = 0;
    private Long teamID;
    private PlayerRole role;

    public Player(String role,Long teamID,String name,Long id){
        this.teamID = teamID;
        if(role.equals("Bowler")) {
            this.role = PlayerRole.BOWLER;
        }
        else {
            this.role = PlayerRole.BATSMAN;
        }
        this.name = name;
        wicketsTaken = 0;
        gotOut = false;
        ballsPlayed = 0;
        this.id = id;
    }
    public Player(){

    }

}
