package com.tekion.GameOfCricket.Enums;

import lombok.Getter;

@Getter
public enum PlayerRole {
    BOWLER("Bowler"),
    BATSMAN("Batsman");

    private final String playerRole;

    PlayerRole(String role) {
        this.playerRole = role;
    }
}
