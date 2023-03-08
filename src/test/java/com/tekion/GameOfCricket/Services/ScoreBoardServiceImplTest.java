package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.PlayerDTO;
import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import com.tekion.GameOfCricket.Enums.PlayerRole;
import com.tekion.GameOfCricket.SQLRepository.ScoreBoardRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ScoreBoardServiceImplTest {

    @Mock
    private ScoreBoardRepository scoreBoardRepository;
    @InjectMocks
    private ScoreBoardServiceImpl scoreBoardService;

    @Test
    void saveStats() {
        TeamDTO team = new TeamDTO();
        team.setPlayers(new ArrayList<>());
        team.getPlayers().add(PlayerDTO.builder()
                        .role(PlayerRole.BATSMAN)
                        .build());
        MatchEntity match = new MatchEntity();
        scoreBoardService.saveStats(team,match);
    }

    @Test
    void getRecordTest() {
        List<ScoreBoardEntity> scores = new ArrayList<>();
        ScoreBoardEntity demo = ScoreBoardEntity.builder()
                .matchId(1L)
                .playerId(1L)
                .build();
        scores.add(demo);
        when(scoreBoardRepository.findAll()).thenReturn(scores);
        Assert.assertEquals(demo,scoreBoardService.getRecord(1L,1L));
        Assert.assertEquals(null,scoreBoardService.getRecord(1L,2L));
    }
}