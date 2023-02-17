package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "Teams")
public class TeamEntity {
    @Id
    private int teamID;
    private String teamName;
    private int totalMatches;
    private int matchesWon;

    public TeamEntity(int teamID,String teamName, int totalMatches, int matchesWon) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.totalMatches = totalMatches;
        this.matchesWon = matchesWon;
    }

    public TeamEntity() {
    }
}
