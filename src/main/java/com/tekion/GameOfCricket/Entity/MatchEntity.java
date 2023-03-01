package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Matches")
@NoArgsConstructor
public class MatchEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long firstTeamID;
    private Long secondTeamID;
    private Long winner;
    private int numberOfOvers;
    private Long seriesID;
    @Transient
    private String runStrategy;

    public MatchEntity(Long firstTeamID, Long secondTeamID, int numberOfOvers) {
        this.firstTeamID = firstTeamID;
        this.secondTeamID = secondTeamID;
        this.numberOfOvers = numberOfOvers;
    }
    public MatchEntity(Long firstTeamID, Long secondTeamID, int numberOfOvers,Long seriesID) {
        this.firstTeamID = firstTeamID;
        this.secondTeamID = secondTeamID;
        this.numberOfOvers = numberOfOvers;
        this.seriesID = seriesID;
    }
}
