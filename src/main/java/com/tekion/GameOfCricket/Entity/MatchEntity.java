package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Matches")
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
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
}
