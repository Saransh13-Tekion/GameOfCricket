package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Players")
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class PlayerEntity extends BaseEntity{
    private String name;
    private int wicketsTaken;
    @Builder.Default
    private int runs = 0;
    @Builder.Default
    private int ballsPlayed = 0;
    private Long teamID;
    private String role;

}
