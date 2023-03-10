package com.tekion.GameOfCricket.Services.runGenerator;

import com.tekion.GameOfCricket.Enums.RunGenerationStrategy;

public class RunGeneratorFactory {
    public static RunGenerationStrategy runGenerationStrategy;
    public static RunGenerator runGenerator(){
        if(RunGenerationStrategy.EQUAL.equals(runGenerationStrategy)) {
            return new EqualRunGenerator();
        }
        else{
            return new WeightedRunGenerator();
        }
    }
}
