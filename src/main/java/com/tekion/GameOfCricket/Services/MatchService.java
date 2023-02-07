package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;

import java.util.Scanner;

public class MatchService {
    String firstTeamName;
    Match currentMatch;
    int tossResult = 0;
    private int target = 0;

    PitchService pitch;

    public MatchService(int totalOvers){
        this.currentMatch = new Match();
        currentMatch.setTotalOvers(totalOvers);
    }

    public void startMatch(){
        tossResult = toss(); // Running toss method
        System.out.print("Number of Players in each team is 11, Please tell me how many bowlers are in one Team ");
        Scanner sc = new Scanner(System.in);
        int noOfBowlers = sc.nextInt();
        currentMatch.setFirstTeam(new Team("India",noOfBowlers)); // Initializing first team
        currentMatch.setSecondTeam(new Team("Australia",noOfBowlers)); // Initializing second team
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
        ScoreBoardService.printScoreBoard(currentMatch.getFirstTeam());
        ScoreBoardService.printScoreBoard(currentMatch.getSecondTeam());
    }

    // Method for paying innings, taking team and current innings argument
    // isFirstInnings will be true for first innings and false for second innings
    public void play(Team team, boolean isFirstInnings){
        this.pitch = new PitchService();
        pitch.openers(team.getPlayers().get(0),team.getPlayers().get(1));
        int index = 2;
        int totalBalls = currentMatch.getTotalOvers();
        int currentOver = 0;
        int currentBall = 0;
        for(; currentOver < currentMatch.getTotalOvers(); currentOver++){
            if(team.isAllOut() == true)
                break;
            for(currentBall = 0; currentBall <6; currentBall++){
                if(team.getWickets() == 10) {
                    team.setAllOut(true);
                    break;
                }
                int currRuns = PlayerService.getRuns(pitch.getStriker());
                pitch.getStriker().setBallsPlayed(pitch.getStriker().getBallsPlayed()+1);
                if(currRuns == 7) {
                    team.setWickets(team.getWickets() + 1);
                    pitch.getStriker().setGotOut(true);
                    if(index <= 10) {
                        pitch.getOut(team.getPlayers().get(index));
                        index++;
                    }
                }
                else {
                    pitch.getStriker().setRuns(pitch.getStriker().getRuns() + currRuns);
                    team.setTotalRuns(team.getTotalRuns() + currRuns);
                    if (currRuns % 2 == 1) {
                        pitch.swap();
                    }
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
        if(team.isAllOut()){
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
