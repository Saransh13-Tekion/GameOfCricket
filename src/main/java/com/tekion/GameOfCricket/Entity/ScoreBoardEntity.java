package com.tekion.GameOfCricket.Entity;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "ScoreBoard")
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class ScoreBoardEntity extends BaseEntity{
    private Long teamId;
    private Long matchId;
    private Long playerId;
    private int runsScored;
    private int wicketsTaken;
    private int ballsPlayed;
    private String playerRole;

}
