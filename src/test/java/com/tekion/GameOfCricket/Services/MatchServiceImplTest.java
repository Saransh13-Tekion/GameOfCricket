package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.SQLRepository.MatchRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class MatchServiceImplTest {

    @Mock
    MatchRepository matchRepository;
    @Mock
    InningServiceImpl inningService;
    @Mock
    PlayerServiceImpl playerService;
    @Mock
    TeamServiceImpl teamService;
    @Mock
    ScoreBoardServiceImpl scoreBoardService;
    @InjectMocks
    MatchServiceImpl matchService;

    MatchEntity match;

    @BeforeEach
    void before(){
        MatchEntity match = new MatchEntity();
        match.setId(1L);
        match.setSecondTeamID(1L);
        match.setSecondTeamID(2L);
    }

    @Test
    void startMatchMissingDataTest() throws ValidationException, MissingDataException {
        when(matchRepository.findById(1L)).thenReturn(Optional.ofNullable(match));
        try {
            matchService.startMatch(1L);
        }
        catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Required match not Found in Database");
        }
    }

    @Test
    void startMatchTest() throws ValidationException, MissingDataException{
        match = new MatchEntity();
        match.setId(1L);
        TeamEntity firstTeam = TeamEntity.builder()
                .teamName("India")
                .build();
        TeamEntity secondTeam = TeamEntity.builder()
                .teamName("Australia")
                .build();

        firstTeam.setId(1L);
        secondTeam.setId(2L);
        match.setFirstTeamID(1L);
        match.setSecondTeamID(2L);
        when(teamService.getTeam(1L)).thenReturn(firstTeam);
        when(teamService.getTeam(2L)).thenReturn(secondTeam);
        when(matchRepository.findById(1L)).thenReturn(Optional.ofNullable(match));
        matchService.startMatch(1L);
        Assert.assertTrue(!match.getUpdatedAt().equals(null));
    }

    @Test
    void getDetailsTest() {
        when(matchRepository.findById(1L)).thenReturn(Optional.ofNullable(match));

        Assert.assertEquals(match,matchService.getDetails(1L));
    }

    @Test
    void createMatchTest() {
        match = new MatchEntity();
        when(matchRepository.save(match)).thenReturn(match);
        matchService.createMatch(match);

        Assert.assertFalse(match.getCreatedAt().equals(null));
    }
}