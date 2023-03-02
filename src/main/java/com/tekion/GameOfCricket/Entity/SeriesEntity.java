package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Series")
@EqualsAndHashCode(callSuper=false)
public class SeriesEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long firstTeamID;
    private Long secondTeamID;
    private Long winner;
    private int numberOfOvers;
    private int numberOfMatches;
    private int matchesFirstTeamWon;
    private int matchesSecondTeamWon;

}
