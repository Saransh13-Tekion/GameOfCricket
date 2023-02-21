package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class InningServiceImpl implements InningService{
    @Autowired
    private PitchService pitch;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private Match currentMatch;
    private int target = 0;

    public PitchService getPitch() {
        return pitch;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public int getTarget() {
        return target;
    }

    @Override
    public void play(Team battingTeam, boolean isFirstInnings, Team bowlingTeam){
        int totalWickets = battingTeam.getPlayers().size() - 1;
        System.out.println(totalWickets);
        ArrayList<Player> allBowlers = new ArrayList<>();
        for(Player player : bowlingTeam.getPlayers()){
            if(player.getRole() == PlayerRole.BOWLER){
                allBowlers.add(player);
            }
        }
        pitch.openers(battingTeam.getPlayers().get(0), battingTeam.getPlayers().get(1));
        Player striker = pitch.getStriker();
        Player nonStriker = pitch.getNonStriker();
        Player currentBowler = allBowlers.get(0);
        Collections.shuffle(allBowlers);
        int index = 2;
        int currentOver = 0;
        int currentBall = 0;
        for(; currentOver < currentMatch.getTotalOvers(); currentOver++){
            if(battingTeam.isAllOut())
                break;
            currentBowler = changeBowler(currentBowler,allBowlers);
            for(currentBall = 0; currentBall < Constants.ballsInAnOver; currentBall++){
                if(battingTeam.getWickets() == totalWickets) {
                    battingTeam.setAllOut(true);
                    break;
                }
                int currRuns = playerService.getRuns(striker);
                striker.setBallsPlayed(striker.getBallsPlayed()+1);
                if(currRuns == Constants.wicketBall) {
                    battingTeam.setWickets(battingTeam.getWickets() + 1);
                    striker.setGotOut(true);
                    currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
                    if(index <= totalWickets) {
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
                if(!isFirstInnings){
                    if(battingTeam.getTotalRuns() >= target){
                        System.out.println(battingTeam.getName() + " has scored " + battingTeam.getTotalRuns() + " runs and lost " + battingTeam.getWickets() + " wickets in " + (currentOver) + "." + (currentBall) + " Overs.");
                        System.out.println(battingTeam.getName() + " won the match by " + (Constants.totalWickets - battingTeam.getWickets()) + " wickets.");
                        currentMatch.setWinner(battingTeam.getTeamID());
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
            System.out.println(bowlingTeam.getName() + " won the match by " + (target - battingTeam.getTotalRuns()) + " runs.");
            currentMatch.setWinner(bowlingTeam.getTeamID());
        }
    }

    @Override
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
}
