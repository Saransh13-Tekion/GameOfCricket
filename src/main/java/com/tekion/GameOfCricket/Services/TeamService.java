package com.tekion.GameOfCricket.Services;

import com.github.javafaker.Faker;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Utilities.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeamService{
    void addTeam(List<TeamEntity> team);
    TeamEntity getTeam(Long id);
}
