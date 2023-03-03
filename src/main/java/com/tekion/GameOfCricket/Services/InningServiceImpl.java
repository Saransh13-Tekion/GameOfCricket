package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Services.runGenerator.RunGenerator;
import com.tekion.GameOfCricket.Services.runGenerator.RunGeneratorFactory;
import com.tekion.GameOfCricket.Utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class InningServiceImpl implements InningService{
    @Autowired
    private PitchService pitchService;
    private int target = 0;
    private int totalWickets;
    private int currentBatsmanNumber = 0;

    // Method for paying innings, taking battingTeam and current innings argument
    // isFirstInnings will be true for first innings and false for second innings
    //This function will take both batting and bowling team.
    @Override
    public void play(Match currentMatch,Team battingTeam, boolean isFirstInnings, Team bowlingTeam) throws ValidationException {
        totalWickets = battingTeam.getPlayers().size() - 1;
        ArrayList<Player> allBowlers = getAllBowlers(bowlingTeam);
        if(allBowlers.size() <=1)
            throw new ValidationException("There must be at least 2 bowlers in the team");
        Collections.shuffle(allBowlers);
        pitchService.setOpeners(battingTeam.getPlayers().get(0), battingTeam.getPlayers().get(1));
        currentBatsmanNumber++;
        pitchService.setCurrentBowler(allBowlers.get(0));
        Player striker = pitchService.getStriker();
        Player currentBowler = pitchService.getCurrentBowler();
        RunGenerator runGenerator = RunGeneratorFactory.runGenerator();
        int currentOver = 0;
        int currentBall;
        for(; currentOver < currentMatch.getTotalOvers(); currentOver++){
            if(battingTeam.isAllOut())
                break;
            currentBowler = changeBowler(currentBowler,allBowlers);
            for(currentBall = 0; currentBall < Constants.BALLS_IN_AN_OVER; currentBall++){
                if(battingTeam.getWickets() == totalWickets) {
                    battingTeam.setAllOut(true);
                    break;
                }
                int currRuns = runGenerator.generateRuns(striker.getRole());
                ballOperation(battingTeam,currRuns);
                if(!isFirstInnings){
                    if(battingTeam.getTotalRuns() >= target){
                        currentMatch.setWinner(battingTeam.getTeamID());
                        return;
                    }
                }
            }
        }
        if(isFirstInnings) {
            target = battingTeam.getTotalRuns() + 1;
        }
        else{
            currentMatch.setWinner(bowlingTeam.getTeamID());
        }
    }

    private void ballOperation(Team battingTeam,int currRuns){
        Player striker = pitchService.getStriker();
        Player nonStriker = pitchService.getNonStriker();
        Player currentBowler = pitchService.getCurrentBowler();
        striker.setBallsPlayed(striker.getBallsPlayed()+1);
        if(currRuns == Constants.WICKET_BALL) {
            battingTeam.setWickets(battingTeam.getWickets() + 1);
            striker.setGotOut(true);
            currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
            if(currentBatsmanNumber <= totalWickets) {
                pitchService.setStriker(battingTeam.getPlayers().get(currentBatsmanNumber));
                striker = pitchService.getStriker();
                currentBatsmanNumber++;
            }
        }
        else {
            striker.setRuns(striker.getRuns() + currRuns);
            battingTeam.setTotalRuns(battingTeam.getTotalRuns() + currRuns);
            if (currRuns % 2 == 1) {
                pitchService.swap();
                striker = nonStriker;
                nonStriker = pitchService.getNonStriker();
            }
        }
    }

    private ArrayList<Player> getAllBowlers(Team bowlingTeam){
        ArrayList<Player> bowlers = new ArrayList<>();
        for(Player player : bowlingTeam.getPlayers()){
            if(PlayerRole.BOWLER.equals(player.getRole())){
                bowlers.add(player);
            }
        }
        return bowlers;
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
