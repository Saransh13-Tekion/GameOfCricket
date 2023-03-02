package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.Models.Player;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Repository.PlayerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PlayerServiceImplTest<e> {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerServiceImpl playerService;

    private Team firstTeam, secondTeam;
    private List<PlayerEntity> players;

    @BeforeEach
    public void beforeMethod(){
        firstTeam = Team.builder().teamID(1L).build();
        firstTeam.setPlayers(new ArrayList<>());
        secondTeam = Team.builder().teamID(2L).build();
        secondTeam.setPlayers(new ArrayList<>());
        players = new ArrayList<>();
    }

    @Test
    public void testSetPlayers() throws ValidationException {
        players.add(PlayerEntity.builder().teamID(1L).build());
        players.add(PlayerEntity.builder().teamID(2L).build());

        when(playerRepository.findAll()).thenReturn(players);

        playerService.setPlayers(firstTeam,secondTeam);
        Assert.assertEquals(1,firstTeam.getPlayers().size());
    }

    @Test
    public void setPlayerValidationFailureTest(){
        players.add(PlayerEntity.builder().teamID(1L).build());

        when(playerRepository.findAll()).thenReturn(players);

        try{
            playerService.setPlayers(firstTeam,secondTeam);
        }
        catch(Exception e){
            Assert.assertEquals(e.getMessage(),"Unequal number of Players in both teams");
        }
    }

}