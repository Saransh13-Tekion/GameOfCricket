package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import org.springframework.stereotype.Service;

@Service
public class PitchServiceImpl implements PitchService{
    private Player striker, nonStriker, currentBowler;

    @Override
    public Player getStriker() {
        return striker;
    }

    @Override
    public Player getNonStriker() {
        return nonStriker;
    }

    @Override
    public void setOpeners(Player striker, Player nonStriker){
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
    public void setStriker(Player player){
        this.striker = player;
    }

    @Override
    public Player getCurrentBowler(){
        return currentBowler;
    }

    @Override
    public void setCurrentBowler(Player currentBowler) {
        this.currentBowler = currentBowler;
    }
}
