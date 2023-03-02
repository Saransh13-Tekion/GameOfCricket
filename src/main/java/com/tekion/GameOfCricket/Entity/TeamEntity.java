package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "Teams")
@EqualsAndHashCode(callSuper=false)
public class TeamEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamID;
    private String teamName;
    private int totalMatches;
    private int matchesWon;
}
