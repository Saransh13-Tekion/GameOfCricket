package com.tekion.GameOfCricket.Models;

import com.tekion.GameOfCricket.Models.Team;

public class Match {
    Team firstTeam, secondTeam;
    String firstTeamName;
    int tossResult = 0;
    private int target = 0;
    private int totalOvers = 0;
    public Match(int totalOvers){
        firstTeam = new Team("India"); // Initializing first team
        secondTeam = new Team("Australia"); // Initializing second team
        tossResult = toss(); // Running toss method
        this.totalOvers = totalOvers;
        if(tossResult == 0) { // Playing match according to the output of toss
            this.firstTeamName = firstTeam.getName();
            play(firstTeam,true);
            play(secondTeam,false);
        }
        else {
            this.firstTeamName = secondTeam.getName();
            play(secondTeam,true);
            play(firstTeam,false);
        }
    }

    // Method for paying innings, taking team and current innings argument
    // isFirstInnings will be true for first innings and false for second innings
    public void play(Team team, boolean isFirstInnings){
        int currentOver = 0;
        int currentBall = 0;
        boolean allout = false;
        for(; currentOver < this.totalOvers; currentOver++){
            if(allout == true) {
                break;
            }
            for(currentBall = 0; currentBall <6; currentBall++){
                if(team.getWickets() == 10) {
                    allout = true;
                    break;
                }
                int curr_runs = team.players.get(team.getWickets()).getRuns();
                if(curr_runs == 7) {
                    team.setWickets(team.getWickets() + 1);
                }
                else {
                    team.setTotalRuns(team.getTotalRuns() + curr_runs);
                }
                if(isFirstInnings == false){
                    if(team.getTotalRuns() >= target){
                        System.out.println(team.getName() + " has scored " + team.getTotalRuns() + " runs and lost " + team.getWickets() + " wickets in " + (currentOver) + "." + (currentBall) + " Overs.");
                        System.out.println(team.getName() + " won the match by " + (10 - team.getWickets()) + " wickets.");
                        return;
                    }
                }
            }
        }
        if(allout){
            System.out.println(team.getName() + " has scored " + team.getTotalRuns() + " runs and lost " + team.getWickets() + " wickets in " + (currentOver) + "." + currentBall + " Overs.");
        }
        else {
            System.out.println(team.getName() + " has scored " + team.getTotalRuns() + " runs and lost " + team.getWickets() + " wickets in " + (currentOver) + ".0 Overs.");
        }
        if(isFirstInnings) {
            target = team.getTotalRuns() + 1;
        }
        else{
            System.out.println(this.firstTeamName + " won the match by " + (target - team.getTotalRuns()) + " runs.");
        }
    }
    public int toss() {
        return (int) (Math.random() * 2);
    }
}
