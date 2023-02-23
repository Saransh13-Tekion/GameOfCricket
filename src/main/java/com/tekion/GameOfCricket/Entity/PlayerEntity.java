package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int wicketsTaken;
    private int runs = 0;
    private int ballsPlayed = 0;
    private Long teamID;

    @Column(name = "Role")
    private String role;
}
