package com.tekion.GameOfCricket.Models;

import lombok.Data;


@Data
public class Match {
    private Team firstTeam, secondTeam;
    private int totalOvers = 0;
    private String winner;
}
