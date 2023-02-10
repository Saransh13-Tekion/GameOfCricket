package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Models.Player;

import java.util.List;

public interface TeamService{
    public void makeTeam(List<Player> players, int noOfBowlers);
}
