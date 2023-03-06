package com.tekion.GameOfCricket.Enums;

import lombok.Getter;

@Getter
public enum RunGenerationStrategy {
    EQUAL("Equal"),
    WEIGHTED("Weighted");

    public final String runGenerationStrategy;

    RunGenerationStrategy(String strategy) {
        this.runGenerationStrategy = strategy;
    }
}
