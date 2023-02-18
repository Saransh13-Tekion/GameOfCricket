package com.tekion.GameOfCricket.Entity;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ScoreBoard")
@NoArgsConstructor
public class ScoreBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Team ID")
    private Long teamId;
    @Column(name = "Match ID")
    private Long matchId;
    private Long playerId;
    @Column(name = "Runs Scored")
    private int runsScored;
    @Column(name = "Wickets Taken")
    private int wicketsTaken;
    @Column(name = "Balls Played")
    private int ballsPlayed;
    @Column(name = "Player Role")
    private String playerRole;

    public ScoreBoardEntity(Long teamId, Long matchId, Long playerId, int runsScored, int wicketsTaken, int ballsPlayed, PlayerRole playerRole) {
        this.teamId = teamId;
        this.matchId = matchId;
        this.playerId = playerId;
        this.runsScored = runsScored;
        this.wicketsTaken = wicketsTaken;
        this.ballsPlayed = ballsPlayed;
        if(playerRole == PlayerRole.BOWLER){
            this.playerRole = "Bowler";
        }
        else{
            this.playerRole = "Batsman";
        }
    }
}
