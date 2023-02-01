package com.tekion.GameOfCricket;

import java.util.ArrayList;

public class Team {
    ArrayList<Player>players;

    String name;
    int totalRuns = 0;
    int wickets = 0;

    public Team(String name){
        this.name = name;
        this.players = new ArrayList<Player>();
        for(int i = 0;i<11;i++){
            players.add(new Player());
        }
    }
    public int total(){
        return 0;
    }
}
