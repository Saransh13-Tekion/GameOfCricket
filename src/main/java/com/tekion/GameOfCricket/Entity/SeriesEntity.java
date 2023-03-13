package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "First Team ID Cannot be Empty")
    private Long firstTeamID;
    @NotNull(message = "Second Team ID Cannot be Empty")
    private Long secondTeamID;
    private Long winner;
    @Positive
    @NotNull
    private int numberOfOvers;
    @Positive
    @NotNull
    private int numberOfMatches;
    private int matchesFirstTeamWon;
    private int matchesSecondTeamWon;

}
