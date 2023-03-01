package com.tekion.GameOfCricket.Entity;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ScoreBoard")
@NoArgsConstructor
public class ScoreBoardEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long teamId;
    private Long matchId;
    private Long playerId;
    private int runsScored;
    private int wicketsTaken;
    private int ballsPlayed;
    private String playerRole;

    public ScoreBoardEntity(Long teamId, Long matchId, Long playerId, int runsScored, int wicketsTaken, int ballsPlayed, PlayerRole playerRole) {
        this.teamId = teamId;
        this.matchId = matchId;
        this.playerId = playerId;
        this.runsScored = runsScored;
        this.wicketsTaken = wicketsTaken;
        this.ballsPlayed = ballsPlayed;
        this.playerRole = playerRole.getPlayerRole();
    }
}
