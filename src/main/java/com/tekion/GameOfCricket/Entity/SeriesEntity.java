package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Series")
@EqualsAndHashCode(callSuper=false)
public class SeriesEntity extends BaseEntity{
    @NotEmpty(message = "First Team ID Cannot be Empty")
    private Long firstTeamID;
    @NotEmpty(message = "Second Team ID Cannot be Empty")
    private Long secondTeamID;
    private Long winner;
    @Positive
    @NotEmpty
    private int numberOfOvers;
    @Positive
    @NotEmpty
    private int numberOfMatches;
    private int matchesFirstTeamWon;
    private int matchesSecondTeamWon;

}
