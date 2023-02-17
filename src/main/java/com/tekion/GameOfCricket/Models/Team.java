package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Repository.PlayerRepository;
import com.tekion.GameOfCricket.Services.TeamServiceImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Team {

    List<Player> players;
    private String name;
    private int totalRuns = 0;
    private int wickets = 0;
    private int oversPlayed = 0;
    private int ballsPlayed = 0;
    boolean isAllOut = false;
    private int teamID;
    private int totalMatches = 0;

    //Initializing Team arraylist
    public Team(String name, int noOfBowlers,TeamServiceImpl teamService,int teamID){
        this.name = name;
        this.players = new ArrayList<Player>();
        this.teamID = teamID;
        teamService.makeTeam(this.players,noOfBowlers,teamID);
    }

}
