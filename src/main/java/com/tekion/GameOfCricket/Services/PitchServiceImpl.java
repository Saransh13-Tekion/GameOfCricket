package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class PitchServiceImpl implements PitchService{
    private Player striker, nonStriker;

    public Player getStriker() {
        return striker;
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }

    @Override
    public void openers(Player striker, Player nonStriker){
        this.striker = striker;
        this.nonStriker = nonStriker;
    }

    @Override
    public void swap(){
        Player temp = this.striker;
        this.striker = this.nonStriker;
        this.nonStriker = temp;
    }

    @Override
    public void nextPlayer(Player player){
        this.striker = player;
    }
}
