package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Teams")
public class TeamEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamID;
    private String teamName;
    private int totalMatches;
    private int matchesWon;

}
