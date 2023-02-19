package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import com.tekion.GameOfCricket.Enums.*;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Repository.ScoreBoardRepository;
import com.tekion.GameOfCricket.Utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreBoardServiceImpl implements ScoreBoardService{

    @Autowired
    private ScoreBoardRepository scoreBoardRepository;

    @Override
    public void saveStats(Team team, MatchEntity match){
        for(Player player:team.getPlayers()){
            scoreBoardRepository.save(new ScoreBoardEntity(team.getTeamID(),match.getId(),player.getId(),player.getRuns(), player.getWicketsTaken(), player.getBallsPlayed(),player.getRole()));
        }
    }

    @Override
    public ScoreBoardEntity getRecord(ScoreBoardEntity requiredRecord){
        ArrayList<ScoreBoardEntity> records = (ArrayList<ScoreBoardEntity>) scoreBoardRepository.findAll();
        for(ScoreBoardEntity record : records){
            if(record.getPlayerId() == requiredRecord.getPlayerId() && record.getMatchId()==requiredRecord.getMatchId()){
                return record;
            }
        }
        return null;
    }

    @Override
    public void printScoreBoard(Team team){
            System.out.println(team.getName() + ":");
            System.out.println("Total Runs Scored: " + team.getTotalRuns());
            System.out.println("Batsman\t\t\t\t\t\tRuns\tBalls");
            int playerNotBatted = Constants.totalPlayers;
            for(Player player:team.getPlayers()){
                if(player.getBallsPlayed() != 0) {
                    int bufferSize = 20 - player.getName().length();
                    System.out.print(player.getName());

                    for(int i=0;i<bufferSize;i++){
                        System.out.print(" ");
                    }

                    System.out.print("\t\t");
                    if (player.isGotOut()) {
                        System.out.println(player.getRuns() + "\t\t" + player.getBallsPlayed());
                    } else {
                        System.out.println(player.getRuns() + "*\t\t" + player.getBallsPlayed());
                    }
                    playerNotBatted--;
                }
            }
            if(playerNotBatted >0) {
                System.out.print("Yet to Bat: ");
                for (Player player : team.getPlayers()) {
                    if (player.getBallsPlayed() == 0) {
                        System.out.print(player.getName() + " ");
                    }
                }
            }
            System.out.println("");
            System.out.println("Bowling Statistics");
            System.out.println("Bowlers \t\t\t Wickets");
            for(Player bowler : team.getPlayers()){
                if(bowler.getRole() == PlayerRole.BOWLER){
                    int bufferSize = 20 -bowler.getName().length();
                    System.out.print(bowler.getName());
                    for(int i=0;i<bufferSize;i++){
                        System.out.print(" ");
                    }
                    System.out.println("\t\t" + bowler.getWicketsTaken());
                }
            }
        }
}
