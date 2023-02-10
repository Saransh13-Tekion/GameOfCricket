package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import lombok.Data;

@Data
public class PitchServiceImpl implements PitchService{
    private Player striker, nonStriker;
    public void openers(Player striker, Player nonStriker){
        this.striker = striker;
        this.nonStriker = nonStriker;
    }

    public void swap(){
        Player temp = this.striker;
        this.striker = this.nonStriker;
        this.nonStriker = temp;
    }

    public void nextPlayer(Player player){
        this.striker = player;
    }
}
