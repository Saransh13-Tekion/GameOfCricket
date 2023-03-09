package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.PlayerDTO;
import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.SQLRepository.PlayerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PlayerServiceImplTest {

    @Mock
    PlayerRepository playerRepository;
    @InjectMocks
    PlayerServiceImpl playerService;

    private TeamDTO firstTeam, secondTeam;
    private List<PlayerEntity> players;

    @BeforeEach
    void beforeMethod(){
        firstTeam = TeamDTO.builder().teamID(1L).build();
        firstTeam.setPlayers(new ArrayList<>());
        secondTeam = TeamDTO.builder().teamID(2L).build();
        secondTeam.setPlayers(new ArrayList<>());
        players = new ArrayList<>();
    }

    @Test
    void setPlayersTest() throws ValidationException {
        players.add(PlayerEntity.builder().teamID(1L).build());
        players.add(PlayerEntity.builder().teamID(2L).build());

        when(playerRepository.findAll()).thenReturn(players);

        playerService.setPlayers(firstTeam,secondTeam);
        Assert.assertEquals(1,firstTeam.getPlayers().size());
    }

    @Test
    void setPlayerValidationFailureTest(){
        players.add(PlayerEntity.builder().teamID(1L).build());

        when(playerRepository.findAll()).thenReturn(players);

        try{
            playerService.setPlayers(firstTeam,secondTeam);
        }
        catch(Exception e){
            Assert.assertEquals(e.getMessage(),"Unequal number of Players in both teams");
        }
    }

    @Test
    void getPlayerTest(){
        PlayerEntity player = new PlayerEntity();
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        PlayerEntity resultPlayer = playerService.getPlayer(1L);

        Assert.assertEquals(resultPlayer,player);
    }

    @Test
    void addPlayerTest(){
        List<PlayerEntity> players = new ArrayList<>();
        players.add(new PlayerEntity());
        playerService.addPlayer(players);
        when(playerRepository.save(players.get(0))).thenReturn(null);

        Assert.assertFalse(players.get(0).getCreatedAt().equals(null));
    }

    @Test
    void saveStatsTest() throws MissingDataException {
        PlayerEntity player = new PlayerEntity();
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        firstTeam.getPlayers().add(PlayerDTO.builder()
                        .id(1L)
                        .runs(100)
                        .build());
        playerService.saveStats(firstTeam);

        Assert.assertEquals(100,player.getRuns());
    }

    @Test
    void saveStatsMissingDataTest(){
        try{
            playerService.saveStats(firstTeam);
        } catch (MissingDataException e) {
            Assert.assertEquals(e.getMessage(),"Required team not Found in Database");
        }
    }
}