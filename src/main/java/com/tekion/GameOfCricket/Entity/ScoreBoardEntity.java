package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ScoreBoard")
public class ScoreBoardEntity {

    private int teamId;

    private int matchId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;
    private int runsScored;
    private int wicketsTaken;
    private int ballsPlayed;

    public ScoreBoardEntity(int matchId, int runsScored, int wicketsTaken, int ballsPlayed) {
        this.matchId = matchId;
        this.runsScored = runsScored;
        this.wicketsTaken = wicketsTaken;
        this.ballsPlayed = ballsPlayed;
    }

    public ScoreBoardEntity() {
    }
}
