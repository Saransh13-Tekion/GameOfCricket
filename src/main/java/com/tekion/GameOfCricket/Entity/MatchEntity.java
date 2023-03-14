package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Entity
@Table(name = "Matches")
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class MatchEntity extends BaseEntity{
    @NotNull(message = "First Team ID Cannot be Empty")
    private Long firstTeamID;
    @NotNull(message = "Second Team ID Cannot be Empty")
    private Long secondTeamID;
    private Long winner;
    @Positive
    private int numberOfOvers;
    private Long seriesID;
    @Transient
    private String runStrategy;
}
