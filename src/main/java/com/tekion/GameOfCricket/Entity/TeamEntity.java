package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "Teams")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TeamEntity extends BaseEntity{
    private String teamName;
    private int totalMatches;
    private int matchesWon;
}
