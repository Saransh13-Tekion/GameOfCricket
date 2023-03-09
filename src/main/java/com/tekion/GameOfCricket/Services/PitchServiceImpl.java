package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.PlayerDTO;
import org.springframework.stereotype.Service;

@Service
public class PitchServiceImpl implements PitchService{
    private PlayerDTO striker, nonStriker, currentBowler;

    @Override
    public PlayerDTO getStriker() {
        return striker;
    }

    @Override
    public PlayerDTO getNonStriker() {
        return nonStriker;
    }

    @Override
    public void setOpeners(PlayerDTO striker, PlayerDTO nonStriker){
        this.striker = striker;
        this.nonStriker = nonStriker;
    }

    @Override
    public void swap(){
        PlayerDTO temp = this.striker;
        this.striker = this.nonStriker;
        this.nonStriker = temp;
    }

    @Override
    public void setStriker(PlayerDTO player){
        this.striker = player;
    }

    @Override
    public PlayerDTO getCurrentBowler(){
        return currentBowler;
    }

    @Override
    public void setCurrentBowler(PlayerDTO currentBowler) {
        this.currentBowler = currentBowler;
    }
}
