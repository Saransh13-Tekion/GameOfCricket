package com.tekion.GameOfCricket.Models;

import java.util.ArrayList;
import java.util.List;

public class Team {

    List<Player> players;
    private String name;
    private int totalRuns = 0;
    private int wickets = 0;

    public List<Player> getPlayers() {
        return players;
    }

    public String getName(){
        return this.name;
    }

    public int getTotalRuns(){
        return this.totalRuns;
    }

    public int getWickets(){
        return this.wickets;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTotalRuns(int totalRuns){
        this.totalRuns = totalRuns;
    }

    public void setWickets(int wickets){
        this.wickets = wickets;
    }

    //Initializing Team arraylist
    public Team(String name){
        this.name = name;
        this.players = new ArrayList<Player>();
        for(int i = 0;i<11;i++){
            players.add(new Player());
        }
    }
}
