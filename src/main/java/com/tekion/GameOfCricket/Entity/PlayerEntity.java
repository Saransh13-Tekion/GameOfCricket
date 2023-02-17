package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Players")
public class PlayerEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "wicketsTaken")
    private int wicketsTaken;
    @Column(name = "runs")
    private int runs = 0;
    @Column(name = "ballsPlayed")
    private int ballsPlayed = 0;
    @Column(name = "teamID")
    private int teamID;


    public PlayerEntity() {
    }

    public PlayerEntity(int ballsPlayed,String name, int runs, int teamID, int wicketsTaken) {
        this.name = name;
        this.wicketsTaken = wicketsTaken;
        this.runs = runs;
        this.ballsPlayed = ballsPlayed;
        this.teamID = teamID;
    }
}
