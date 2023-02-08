package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;

public class ScoreBoardService {
    public static void printScoreBoard(Team team){
        System.out.println(team.getName() + ":");
        System.out.println("Total Runs Scored: " + team.getTotalRuns());
        System.out.println("Batsman\t\t\tRuns\tBalls");
        int notBatted = 11;
        for(Player player:team.getPlayers()){
            if(player.getBallsPlayed() != 0) {
                int bufferSize = 10-player.getName().length();
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
                notBatted--;
            }
        }
        if(notBatted>0) {
            System.out.print("Yet to Bat: ");
            for (Player player : team.getPlayers()) {
                if (player.getBallsPlayed() == 0) {
                    System.out.print(player.getName() + " ");
                }
            }
        }
        System.out.println("");
        System.out.println("Bowling Statistics");
        System.out.println("Bowlers \t Wickets");
        for(Player bowler : team.getPlayers()){
            if(bowler.getRole() == "Bowler"){
                int bufferSize = 10-bowler.getName().length();
                System.out.print(bowler.getName());
                for(int i=0;i<bufferSize;i++){
                    System.out.print(" ");
                }
                System.out.println("\t\t" + bowler.getWicketsTaken());
            }
        }
    }
}
