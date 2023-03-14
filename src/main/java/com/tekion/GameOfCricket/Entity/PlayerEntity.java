package com.tekion.GameOfCricket.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Entity
@Table(name = "Players")
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class PlayerEntity extends BaseEntity{
    @NotNull(message = "Player Name Cannot be Empty")
    private String name;
    private int wicketsTaken;
    @Builder.Default
    private int runs = 0;
    @Builder.Default
    private int ballsPlayed = 0;
    @NotNull
    private Long teamID;
    @NotNull
    @Pattern(regexp = "Batsman|Bowler", message = "Invalid player role. Must be either Batsman or Bowler.")
    private String role;

}
