package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.MatchDTO;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.DTO.PlayerDTO;
import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.Services.runGenerator.RunGenerator;
import com.tekion.GameOfCricket.Services.runGenerator.RunGeneratorFactory;
import com.tekion.GameOfCricket.Utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    static Logger log = LogManager.getLogger(InningServiceImpl.class);

    @Override
    public void play(MatchDTO currentMatch, TeamDTO battingTeam, boolean isFirstInnings, TeamDTO bowlingTeam) throws ValidationException {
        target = 0;
        log.info("In the Play match, Batting TeamDTO is " + battingTeam.getName() + ". Bowling TeamDTO is " + bowlingTeam.getName() +".");
        totalWickets = battingTeam.getPlayers().size() - 1;
        ArrayList<PlayerDTO> allBowlers = getAllBowlers(bowlingTeam);
        if(allBowlers.size() <=1) {
            log.error("There are Less than/equal to 1 bowler in " + bowlingTeam.getName());
            throw new ValidationException("There must be at least 2 bowlers in the team");
        }
        Collections.shuffle(allBowlers);
        pitchService.setOpeners(battingTeam.getPlayers().get(0), battingTeam.getPlayers().get(1));
        currentBatsmanNumber++;
        pitchService.setCurrentBowler(allBowlers.get(0));
        PlayerDTO striker = pitchService.getStriker();
        PlayerDTO currentBowler = pitchService.getCurrentBowler();
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
        log.info("Exiting the play Function.");
    }

    private void ballOperation(TeamDTO battingTeam, int currRuns){
        PlayerDTO striker = pitchService.getStriker();
        PlayerDTO nonStriker = pitchService.getNonStriker();
        PlayerDTO currentBowler = pitchService.getCurrentBowler();
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

    private ArrayList<PlayerDTO> getAllBowlers(TeamDTO bowlingTeam){
        ArrayList<PlayerDTO> bowlers = new ArrayList<>();
        for(PlayerDTO player : bowlingTeam.getPlayers()){
            if(PlayerRole.BOWLER.equals(player.getRole())){
                bowlers.add(player);
            }
        }
        return bowlers;
    }

    private PlayerDTO changeBowler(PlayerDTO currentBowler, ArrayList<PlayerDTO>allBowlers){
        log.info("In the change bowler function");
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
