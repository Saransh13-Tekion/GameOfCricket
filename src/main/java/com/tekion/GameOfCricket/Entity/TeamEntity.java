package com.tekion.GameOfCricket.Entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "Teams")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TeamEntity extends BaseEntity{
    private String teamName;
    private int totalMatches;
    private int matchesWon;
}
