package com.tekion.GameOfCricket.Models;

public class Player {
    int runs = 0;

    public int getRuns(){
        return (int)(Math.random() * 8);
    }
}
