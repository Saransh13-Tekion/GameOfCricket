package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Services.TeamService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Team {

    List<Player> players;
    private String name;
    private int totalRuns = 0;
    private int wickets = 0;
    private int oversPlayed = 0;
    private int ballsPlayed = 0;
    boolean isAllOut = false;

    //Initializing Team arraylist
    public Team(String name,int noOfBowlers,TeamService teamService){
        this.name = name;
        this.players = new ArrayList<Player>();
        teamService.makeTeam(this.players,noOfBowlers);
    }
}
