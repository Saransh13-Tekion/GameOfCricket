package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class PitchServiceImpl implements PitchService{
    private Player striker, nonStriker;
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
