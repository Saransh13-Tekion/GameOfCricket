package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Wickets Taken")
    private int wicketsTaken;
    @Column(name = "Runs Made")
    private int runs = 0;
    @Column(name = "Balls Played")
    private int ballsPlayed = 0;
    @Column(name = "TeamID")
    private Long teamID;

    @Column(name = "Role")
    private Long role;
}
