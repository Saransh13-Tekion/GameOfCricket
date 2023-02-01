package com.tekion.GameOfCricket;

import java.util.Scanner;

public class Match {
    Team first_team,second_team;
    String first_team_name;
    int toss_result = 0;
    int target = 0;
    int overs = 0;
    public Match(){
        first_team = new Team("India"); // Initializing first team
        second_team = new Team("Australia"); // Initializing second team
        toss_result = toss(); // Running toss method
        System.out.println("Enter Number of Overs: ");
        Scanner sc = new Scanner(System.in);
        this.overs = sc.nextInt(); // Asking User for Number of Overs.
        if(toss_result == 0) { // Playing match according to the output of toss
            this.first_team_name = first_team.name;
            play(first_team,true);
            play(second_team,false);
        }
        else {
            this.first_team_name = second_team.name;
            play(second_team,true);
            play(first_team,false);
        }
    }

    // Method for paying innings, taking team and current innings argument
    // isFirstInnings will be true for first innings and false for second innings
    public void play(Team team, boolean isFirstInnings){
        for(int over = 0;over < this.overs; over++){
            for(int ball = 0;ball<6;ball++){
                if(team.wickets == 10) {
                    break;
                }
                int curr_runs = team.players.get(team.wickets).getRuns();
                if(curr_runs == 7)
                    team.wickets++;
                else
                    team.totalRuns += curr_runs;
                if(isFirstInnings == false){
                    if(team.totalRuns >= target){
                        System.out.println(team.name + " has scored " + team.totalRuns + " runs and lost " + team.wickets + " wickets.");
                        System.out.println(team.name + " won the match by " + (10 - team.wickets) + " wickets.");
                        return;
                    }
                }
            }
        }
        System.out.println(team.name + " has scored " + team.totalRuns + " runs and lost " + team.wickets + " wickets.");
        if(isFirstInnings) {
            target = team.totalRuns + 1;
        }
        else{
            System.out.println(this.first_team_name + " won the match by " + (target - team.totalRuns) + " runs.");
        }
    }
    public int toss(){
        return (int)(Math.random()*2);
    }
}
