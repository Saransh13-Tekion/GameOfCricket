package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Series")
public class SeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long firstTeamID;
    private Long secondTeamID;
    private Long winner;
    private int numberOfOvers;
    private int numberOfMatches;

}