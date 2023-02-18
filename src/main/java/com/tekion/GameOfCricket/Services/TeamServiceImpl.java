package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Repository.TeamRepository;
import com.tekion.GameOfCricket.Utilities.Constants;
import com.tekion.GameOfCricket.Models.Player;
import com.github.javafaker.Faker;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void addTeam(List<TeamEntity> teams){
        for(TeamEntity team : teams) {
            teamRepository.save(team);
        }
    }

    @Override
    public TeamEntity getTeam(Long id){return teamRepository.findById(id) .orElse(null);};

}
