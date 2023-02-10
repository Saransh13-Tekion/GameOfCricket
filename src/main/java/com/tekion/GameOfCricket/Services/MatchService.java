package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MatchService{
    String firstTeamName;
    Match currentMatch;
    int tossResult = 0;
    private int target = 0;
    private PlayerService playerService;
    PitchService pitch;
    ScoreBoardService scoreBoardService;

    public MatchService(int totalOvers,PlayerService playerService){
        this.playerService = playerService;
        this.currentMatch = new Match();
        this.scoreBoardService = new ScoreBoardService();
        currentMatch.setTotalOvers(totalOvers);
    }

    public void startMatch(){
        tossResult = toss(); // Running toss method
        System.out.print("Number of Players in each team is 11, Please tell me how many bowlers are in one Team(Valid Input: 3-7): ");
        Scanner sc = new Scanner(System.in);
        int noOfBowlers = sc.nextInt();
        currentMatch.setFirstTeam(new Team("India",noOfBowlers,new TeamService())); // Initializing first team
        currentMatch.setSecondTeam(new Team("Australia",noOfBowlers,new TeamService())); // Initializing second team

        if(tossResult == 0) { // Playing match according to the output of toss
            this.firstTeamName = currentMatch.getFirstTeam().getName();
            play(currentMatch.getFirstTeam(),true,currentMatch.getSecondTeam());
            play(currentMatch.getSecondTeam(),false,currentMatch.getFirstTeam());
        }
        else {
            this.firstTeamName = currentMatch.getSecondTeam().getName();
            play(currentMatch.getSecondTeam(),true,currentMatch.getFirstTeam());
            play(currentMatch.getFirstTeam(),false,currentMatch.getSecondTeam());
        }
        scoreBoardService.printScoreBoard(currentMatch.getFirstTeam());
        scoreBoardService.printScoreBoard(currentMatch.getSecondTeam());
    }

    // Method for paying innings, taking battingTeam and current innings argument
    // isFirstInnings will be true for first innings and false for second innings
    //This function will take both batting and bowling team.
    public void play(Team battingTeam, boolean isFirstInnings,Team bowlingTeam){
        ArrayList<Player>allBowlers = new ArrayList<>();
        for(Player player : bowlingTeam.getPlayers()){
            if(player.getRole() == PlayerRole.BOWLER){
                allBowlers.add(player);
            }
        }
        this.pitch = new PitchService();
        pitch.openers(battingTeam.getPlayers().get(0), battingTeam.getPlayers().get(1));
        Player striker = pitch.getStriker();
        Player nonStriker = pitch.getNonStriker();
        Player currentBowler = allBowlers.get(0);
        Collections.shuffle(allBowlers);
        int index = 2;
        int currentOver = 0;
        int currentBall = 0;
        for(; currentOver < currentMatch.getTotalOvers(); currentOver++){
            if(battingTeam.isAllOut() == true)
                break;
            currentBowler = changeBowler(currentBowler,allBowlers);
            for(currentBall = 0; currentBall < Constants.ballsInAnOver; currentBall++){
                if(battingTeam.getWickets() == Constants.totalWickets) {
                    battingTeam.setAllOut(true);
                    break;
                }
                int currRuns = playerService.getRuns(striker);
                striker.setBallsPlayed(striker.getBallsPlayed()+1);
                if(currRuns == Constants.wicketBall) {
                    battingTeam.setWickets(battingTeam.getWickets() + 1);
                    striker.setGotOut(true);
                    currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
                    if(index <= Constants.totalWickets) {
                        pitch.nextPlayer(battingTeam.getPlayers().get(index));
                        striker = pitch.getStriker();
                        index++;
                    }
                }
                else {
                    striker.setRuns(striker.getRuns() + currRuns);
                    battingTeam.setTotalRuns(battingTeam.getTotalRuns() + currRuns);
                    if (currRuns % 2 == 1) {
                        pitch.swap();
                        striker = nonStriker;
                        nonStriker = pitch.getNonStriker();
                    }
                }
                if(isFirstInnings == false){
                    if(battingTeam.getTotalRuns() >= target){
                        System.out.println(battingTeam.getName() + " has scored " + battingTeam.getTotalRuns() + " runs and lost " + battingTeam.getWickets() + " wickets in " + (currentOver) + "." + (currentBall) + " Overs.");
                        System.out.println(battingTeam.getName() + " won the match by " + (Constants.totalWickets - battingTeam.getWickets()) + " wickets.");
                        return;
                    }
                }
            }
           // pitch.swap();
        }
        if(battingTeam.isAllOut()){
            System.out.println(battingTeam.getName() + " has scored " + battingTeam.getTotalRuns() + " runs and lost " + battingTeam.getWickets() + " wickets in " + (currentOver) + "." + currentBall + " Overs.");
        }
        else {
            System.out.println(battingTeam.getName() + " has scored " + battingTeam.getTotalRuns() + " runs and lost " + battingTeam.getWickets() + " wickets in " + (currentOver) + ".0 Overs.");
        }
        if(isFirstInnings) {
            target = battingTeam.getTotalRuns() + 1;
        }
        else{
            System.out.println(this.firstTeamName + " won the match by " + (target - battingTeam.getTotalRuns()) + " runs.");
        }
    }
    
    public Player changeBowler(Player currentBowler, ArrayList<Player>allBowlers){
        int length = allBowlers.size();
        int index = (int)(Math.random()*length);
        if(currentBowler != null) {
            allBowlers.add(currentBowler);
        }
        currentBowler = allBowlers.get(index);
        allBowlers.remove(index);
        return currentBowler;
    }

    public int toss() {
        return (int) (Math.random() * 2);
    }
}
