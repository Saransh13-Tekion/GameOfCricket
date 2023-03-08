package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.SQLRepository.TeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;
    @InjectMocks
    private TeamServiceImpl teamService;
    TeamEntity team;

    @BeforeEach
    public void beforeMethod(){
        team = new TeamEntity();
        team.setId(1L);
        team.setTeamName("India");
    }
    @Test
    void addTeam() {
        List<TeamEntity> teams = new ArrayList<>();
        teams.add(team);
        when(teamRepository.save(team)).thenReturn(team);
        teamService.addTeam(teams);
        Assert.assertTrue(!team.getCreatedAt().equals(null));
    }

    @Test
    void getTeamTest() throws MissingDataException {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        TeamEntity resultTeam = teamService.getTeam(1L);

        Assert.assertEquals(resultTeam.getTeamName(),team.getTeamName());

    }

    @Test
    void getTeamValidationFailureTest() {
        try{
            teamService.getTeam(1L);
        }
        catch (MissingDataException e){
            Assert.assertEquals("Required team not Found in Database",e.getMessage());
        }
    }

    @Test
    void saveStats() throws MissingDataException {
        MatchEntity match = new MatchEntity();
        match.setFirstTeamID(1L);
        match.setSecondTeamID(2L);
        match.setWinner(1L);
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(teamRepository.findById(2L)).thenReturn(Optional.of(team));
        when(teamRepository.save(team)).thenReturn(team);
        teamService.saveStats(match);
        Assert.assertEquals(2,team.getTotalMatches());
        Assert.assertEquals(1,team.getMatchesWon());
    }
}