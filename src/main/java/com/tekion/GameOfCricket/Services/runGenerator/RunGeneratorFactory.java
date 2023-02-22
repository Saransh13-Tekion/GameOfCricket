package com.tekion.GameOfCricket.Services.runGenerator;

import com.tekion.GameOfCricket.Enums.RunGenerationStrategy;

public class RunGeneratorFactory {
    public static RunGenerationStrategy runGenerationStrategy;
    public static GetRuns runGenerator(){
        if(runGenerationStrategy == RunGenerationStrategy.EQUAL) {
            return new EqualRunGeneration();
        }
        else{
            return new WeightedRunGeneration();
        }
    }
}
