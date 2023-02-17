package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Matches")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long firstTeamID;
    private Long secondTeamID;
    private String winner;
    private int numberOfOvers;
}
