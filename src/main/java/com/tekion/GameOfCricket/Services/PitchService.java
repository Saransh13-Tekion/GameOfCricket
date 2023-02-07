package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import lombok.Data;

@Data
public class PitchService {
    private Player striker, nonStriker;
    public void openers(Player player1, Player player2){
        this.striker = player1;
        this.nonStriker = player2;
    }

    public void swap(){
        Player temp = this.striker;
        this.striker = this.nonStriker;
        this.nonStriker = temp;
    }

    public void getOut(Player player){
        this.striker = player;
    }
}
