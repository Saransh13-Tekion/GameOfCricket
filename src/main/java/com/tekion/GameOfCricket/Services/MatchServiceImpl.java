package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.*;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.*;
import com.tekion.GameOfCricket.Repository.MatchRepository;
import com.tekion.GameOfCricket.Utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MatchServiceImpl implements MatchService{

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private Match currentMatch;
    int tossResult = 0;
    private int target = 0;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PitchService pitch;
    @Autowired
    private ScoreBoardService scoreBoardService ;
    @Autowired
    private TeamService teamService;
    @Override
    public void startMatch(MatchEntity matchEntity){
        tossResult = toss(); // Running toss method
        TeamEntity firstTeam = this.teamService.getTeam(matchEntity.getFirstTeamID());
        TeamEntity secondTeam = this.teamService.getTeam(matchEntity.getSecondTeamID());
        currentMatch.setFirstTeam(firstTeam);
        currentMatch.setSecondTeam(secondTeam);
        currentMatch.getFirstTeam().setPlayers(new ArrayList<>());
        currentMatch.getSecondTeam().setPlayers(new ArrayList<>());
        for(Long numberOfMatches = 1L; numberOfMatches <= matchEntity.getNumberOfMatches(); numberOfMatches++) {
            teamService.resetTeam(currentMatch.getFirstTeam(),currentMatch.getSecondTeam());
            matchEntity.setId(numberOfMatches);
            playerService.setPlayers(currentMatch.getFirstTeam(), currentMatch.getSecondTeam());
            currentMatch.setTotalOvers(matchEntity.getNumberOfOvers());
            if (tossResult == 0) { // Playing match according to the output of toss
                play(currentMatch.getFirstTeam(), true, currentMatch.getSecondTeam());
                play(currentMatch.getSecondTeam(), false, currentMatch.getFirstTeam());
            } else {
                play(currentMatch.getSecondTeam(), true, currentMatch.getFirstTeam());
                play(currentMatch.getFirstTeam(), false, currentMatch.getSecondTeam());
            }
            endMatch(matchEntity);
        }
    }

    private void endMatch(MatchEntity matchEntity){
        scoreBoardService.printScoreBoard(currentMatch.getFirstTeam());
        scoreBoardService.printScoreBoard(currentMatch.getSecondTeam());
        System.out.println("--------------------------------------------------------------------------------------------------");
        matchEntity.setWinner(currentMatch.getWinner());
        matchRepository.save(matchEntity);
        scoreBoardService.saveStats(currentMatch.getFirstTeam(),matchEntity);
        scoreBoardService.saveStats(currentMatch.getSecondTeam(),matchEntity);
        playerService.saveStats(currentMatch.getFirstTeam());
        playerService.saveStats(currentMatch.getSecondTeam());
        teamService.saveStats(matchEntity);
    }

    // Method for paying innings, taking battingTeam and current innings argument
    // isFirstInnings will be true for first innings and false for second innings
    //This function will take both batting and bowling team.
    @Override
    public void play(Team battingTeam, boolean isFirstInnings,Team bowlingTeam){
        int totalWickets = battingTeam.getPlayers().size() - 1;
        System.out.println(totalWickets);
        ArrayList<Player>allBowlers = new ArrayList<>();
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

    @Override
    public int toss() {
        return (int) (Math.random() * 2);
    }
}
