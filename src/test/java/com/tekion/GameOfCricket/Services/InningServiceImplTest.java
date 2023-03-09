package com.tekion.GameOfCricket.Services;


import com.tekion.GameOfCricket.DTO.MatchDTO;
import com.tekion.GameOfCricket.DTO.PlayerDTO;
import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.Exception.ValidationException;;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class InningServiceImplTest {

    @Mock
    PitchServiceImpl pitchService;
    @InjectMocks
    InningServiceImpl inningService;

    MatchDTO match;
    TeamDTO battingTeam,bowlingTeam;

    @BeforeEach
    void beforeMethod(){
        match = new MatchDTO();
        match.setTotalOvers(20);
        battingTeam = TeamDTO.builder()
                .players(new ArrayList<>())
                .build();
        bowlingTeam = TeamDTO.builder()
                .players(new ArrayList<>())
                .build();
        for(int i = 0;i<5;i++){
            PlayerDTO player = PlayerDTO.builder()
                    .role(PlayerRole.BOWLER)
                    .name(i + "player")
                    .build();
            bowlingTeam.getPlayers().add(player);
        }
        for(int i = 0;i<5;i++){
            PlayerDTO player = PlayerDTO.builder()
                    .role(PlayerRole.BATSMAN)
                    .name(i + "player")
                    .build();
            battingTeam.getPlayers().add(player);
        }
    }

    @Test
    void changeBowlerTest(){
        PlayerDTO currentBowler = bowlingTeam.getPlayers().get(0);
        bowlingTeam.getPlayers().remove(0);

        Assert.assertNotEquals(currentBowler,inningService.changeBowler(currentBowler, (ArrayList<PlayerDTO>) bowlingTeam.getPlayers()));
    }

    @Test
    void getAllBowlersTest(){
        ArrayList<PlayerDTO> bowlers = inningService.getAllBowlers(bowlingTeam);
        Assert.assertEquals(bowlers.size(),battingTeam.getPlayers().size());
    }

    @Test
    void ballOperationTest(){
        when(pitchService.getStriker()).thenReturn(battingTeam.getPlayers().get(0));
        when(pitchService.getNonStriker()).thenReturn(battingTeam.getPlayers().get(1));
        when(pitchService.getCurrentBowler()).thenReturn(bowlingTeam.getPlayers().get(0));
        inningService.ballOperation(battingTeam,0);
        Assert.assertEquals(1,battingTeam.getPlayers().get(0).getBallsPlayed());
    }

    @Test
    void playTest() throws ValidationException {
        when(pitchService.getStriker()).thenReturn(battingTeam.getPlayers().get(0));
        when(pitchService.getNonStriker()).thenReturn(battingTeam.getPlayers().get(1));
        when(pitchService.getCurrentBowler()).thenReturn(bowlingTeam.getPlayers().get(0));
        inningService.play(match,battingTeam,true,bowlingTeam);
        Assert.assertNotEquals(0,battingTeam.getTotalRuns());
    }
}