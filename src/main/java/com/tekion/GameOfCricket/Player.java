package com.tekion.GameOfCricket;

public class Player {
    int runs = 0;
    int getRuns(){
        return (int)(Math.random() * 8);
    }
}
