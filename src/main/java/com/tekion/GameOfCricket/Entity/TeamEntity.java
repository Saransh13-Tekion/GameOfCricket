package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Data
@Entity
@Table(name = "Teams")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TeamEntity extends BaseEntity{
    @NotEmpty(message = "Team name Cannot be Empty")
    private String teamName;
    @Positive
    @NotEmpty
    private int totalMatches;
    private int matchesWon;
}
