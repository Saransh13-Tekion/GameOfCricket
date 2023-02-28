package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.*;
import com.tekion.GameOfCricket.Enums.RunGenerationStrategy;
import com.tekion.GameOfCricket.Models.*;
import com.tekion.GameOfCricket.Repository.MatchRepository;
import com.tekion.GameOfCricket.Services.runGenerator.RunGeneratorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private InningService inningService;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ScoreBoardService scoreBoardService;
    @Autowired
    private TeamService teamService;


    @Override
    public Long startMatch(Long matchId) {
        MatchEntity matchEntity = matchRepository.findById(matchId).orElse(null);
        Match currentMatch = new Match();
        currentMatch.setFirstTeam(teamService.getTeam(matchEntity.getFirstTeamID()));
        currentMatch.setSecondTeam(teamService.getTeam(matchEntity.getSecondTeamID()));
        setStrategy(matchEntity);
        int tossResult = toss(); // Running toss method
        playerService.setPlayers(currentMatch.getFirstTeam(), currentMatch.getSecondTeam());
        currentMatch.setTotalOvers(matchEntity.getNumberOfOvers());
        if (tossResult == 0) { // Playing match according to the output of toss
            inningService.play(currentMatch,currentMatch.getFirstTeam(), true, currentMatch.getSecondTeam());
            inningService.play(currentMatch,currentMatch.getSecondTeam(), false, currentMatch.getFirstTeam());
        } else {
            inningService.play(currentMatch,currentMatch.getSecondTeam(), true, currentMatch.getFirstTeam());
            inningService.play(currentMatch,currentMatch.getFirstTeam(), false, currentMatch.getSecondTeam());
        }
        endMatch(matchEntity,currentMatch);
        return currentMatch.getWinner();
    }


    private void setStrategy(MatchEntity matchEntity) {
        if(matchEntity.getRunStrategy().equals("Equal"))
            RunGeneratorFactory.runGenerationStrategy = RunGenerationStrategy.EQUAL;

        RunGeneratorFactory.runGenerationStrategy = RunGenerationStrategy.WEIGHTED;
    }

    private void endMatch(MatchEntity matchEntity,Match currentMatch) {
        scoreBoardService.printScoreBoard(currentMatch.getFirstTeam());
        scoreBoardService.printScoreBoard(currentMatch.getSecondTeam());
        System.out.println("--------------------------------------------------------------------------------------------------");
        matchEntity.setWinner(currentMatch.getWinner());
        matchRepository.save(matchEntity);
        scoreBoardService.saveStats(currentMatch.getFirstTeam(), matchEntity);
        scoreBoardService.saveStats(currentMatch.getSecondTeam(), matchEntity);
        playerService.saveStats(currentMatch.getFirstTeam());
        playerService.saveStats(currentMatch.getSecondTeam());
        teamService.saveStats(matchEntity);
    }

    @Override
    public int toss() {
        return (int) (Math.random() * 2);
    }

    @Override
    public MatchEntity getDetails(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    @Override
    public Long createMatch(MatchEntity match) {
        return matchRepository.save(match).getId();
    }
}
