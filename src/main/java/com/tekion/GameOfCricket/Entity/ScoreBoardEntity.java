package com.tekion.GameOfCricket.Entity;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Entity
@Table(name = "ScoreBoard")
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class ScoreBoardEntity extends BaseEntity{
    @NotEmpty(message = "Team ID field cannot be Empty")
    private Long teamId;
    @NotEmpty(message = "Match ID field cannot be Empty")
    private Long matchId;
    @NotEmpty(message = "Player ID field cannot be Empty")
    private Long playerId;
    private int runsScored;
    private int wicketsTaken;
    private int ballsPlayed;
    @NotNull
    @Pattern(regexp = "Batsman|Bowler", message = "Invalid player role. Must be either Batsman or Bowler.")
    private String playerRole;

}
