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

    private int firstTeamID;
    private int secondTeamID;
    private String winner;

    public MatchEntity(int firstTeamID, int secondTeamID, String winner) {
        this.firstTeamID = firstTeamID;
        this.secondTeamID = secondTeamID;
        this.winner = winner;
    }
}