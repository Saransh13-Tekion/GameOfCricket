package com.tekion.GameOfCricket.Entity;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "ScoreBoard")
@AllArgsConstructor
public class ScoreBoardEntity {

    @Column(name = "Team ID")
    private Long teamId;
    @Column(name = "Match ID")
    private Long matchId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;
    @Column(name = "Runs Scored")
    private int runsScored;
    @Column(name = "Wickets Taken")
    private int wicketsTaken;
    @Column(name = "Balls Played")
    private int ballsPlayed;
    @Column(name = "Player Role")
    private PlayerRole playerRole;

}
