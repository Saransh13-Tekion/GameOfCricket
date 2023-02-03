package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Models.Team;

public class MatchService {
    String firstTeamName;
    Match currentMatch;
    int tossResult = 0;
    private int target = 0;

    public MatchService(int totalOvers){
        currentMatch = new Match();
        currentMatch.setFirstTeam(new Team("India")); // Initializing first team
        currentMatch.setSecondTeam(new Team("Australia")); // Initializing second team
        currentMatch.setTotalOvers(totalOvers);
    }

    public void startMatch(){
        tossResult = toss(); // Running toss method
        if(tossResult == 0) { // Playing match according to the output of toss
            this.firstTeamName = currentMatch.getFirstTeam().getName();
            play(currentMatch.getFirstTeam(),true);
            play(currentMatch.getSecondTeam(),false);
        }
        else {
            this.firstTeamName = currentMatch.getSecondTeam().getName();
            play(currentMatch.getFirstTeam(),true);
            play(currentMatch.getSecondTeam(),false);
        }
    }

    // Method for paying innings, taking team and current innings argument
    // isFirstInnings will be true for first innings and false for second innings
    public void play(Team team, boolean isFirstInnings){
        int currentOver = 0;
        int currentBall = 0;
        boolean allout = false;
        for(; currentOver < currentMatch.getTotalOvers(); currentOver++){
            if(allout == true) {
                break;
            }
            for(currentBall = 0; currentBall <6; currentBall++){
                if(team.getWickets() == 10) {
                    allout = true;
                    break;
                }
                int currRuns = team.getPlayers().get(team.getWickets()).getRuns();
                if(currRuns == 7) {
                    team.setWickets(team.getWickets() + 1);
                }
                else {
                    team.setTotalRuns(team.getTotalRuns() + currRuns);
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
