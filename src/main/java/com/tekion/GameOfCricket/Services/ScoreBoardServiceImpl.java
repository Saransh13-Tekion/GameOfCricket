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

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ScoreBoardServiceImpl implements ScoreBoardService{

    @Autowired
    private ScoreBoardRepository scoreBoardRepository;

    @Override
    public void saveStats(Team team, MatchEntity match){
        for(Player player:team.getPlayers()){
            ScoreBoardEntity scoreBoardEntity = new ScoreBoardEntity(team.getTeamID(),match.getId(),player.getId(),player.getRuns(), player.getWicketsTaken(), player.getBallsPlayed(),player.getRole());
            scoreBoardEntity.setCreatedAt(LocalDateTime.now());
            scoreBoardRepository.save(scoreBoardEntity);
        }
    }

    @Override
    public ScoreBoardEntity getRecord(Long matchId,Long playerId){
        ArrayList<ScoreBoardEntity> records = (ArrayList<ScoreBoardEntity>) scoreBoardRepository.findAll();
        for(ScoreBoardEntity record : records){
            if(record.getPlayerId().equals(playerId) && record.getMatchId().equals(matchId)){
                return record;
            }
        }
        return null;
    }

    @Override
    public void printScoreBoard(Team team) {
        System.out.println(team.getName() + ":");
        printBattingScoreBoard(team);
        printBowlingScoreBoard(team);
    }

    private void printBattingScoreBoard(Team team) {
        System.out.println("Total Runs Scored: " + team.getTotalRuns());
        System.out.println("Batsman\t\t\t\t\t\tRuns\tBalls");
        int playerNotBatted = Constants.TOTAL_PLAYERS;
        for (Player player : team.getPlayers()) {
            if (player.getBallsPlayed() != 0) {
                int bufferSize = 20 - player.getName().length();
                System.out.print(player.getName());

                for (int i = 0; i < bufferSize; i++) {
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
        if (playerNotBatted > 0) {
            System.out.print("Yet to Bat: ");
            for (Player player : team.getPlayers()) {
                if (player.getBallsPlayed() == 0) {
                    System.out.print(player.getName() + " ");
                }
            }
        }
    }

    private void printBowlingScoreBoard(Team team){
        System.out.println("");
        System.out.println("Bowling Statistics");
        System.out.println("Bowlers \t\t\t Wickets");
        for (Player bowler : team.getPlayers()) {
            if (bowler.getRole() == PlayerRole.BOWLER) {
                int bufferSize = 20 - bowler.getName().length();
                System.out.print(bowler.getName());
                for (int i = 0; i < bufferSize; i++) {
                    System.out.print(" ");
                }
                System.out.println("\t\t" + bowler.getWicketsTaken());
            }
        }
    }
}
