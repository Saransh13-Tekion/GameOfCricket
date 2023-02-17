package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Repository.TeamRepository;
import com.tekion.GameOfCricket.Utilities.Constants;
import com.tekion.GameOfCricket.Models.Player;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private static TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public static void addInTeamTable(TeamEntity team){
        teamRepository.save(team);
    }
    public static void makeTeam(List<Player> players, int noOfBowlers, int teamID){
        Faker faker = new Faker();
        for(int i = 0; i< Constants.totalPlayers ; i++){
            if(i<Constants.totalPlayers - noOfBowlers) {
                players.add(new Player(PlayerRole.BATSMAN,teamID));
            }
            else{
                players.add(new Player(PlayerRole.BOWLER,teamID));
            }
            players.get(i).setName(faker.name().firstName() + " " + faker.name().lastName());
        }
    }
}
