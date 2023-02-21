package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.*;
import com.tekion.GameOfCricket.Models.*;
import com.tekion.GameOfCricket.Repository.MatchRepository;
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
    private Match currentMatch;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ScoreBoardService scoreBoardService;
    @Autowired
    private TeamService teamService;

    @Override
    public Long startMatch(Long id) {
        MatchEntity matchEntity = matchRepository.findById(id).orElse(null);
        TeamEntity firstTeam = this.teamService.getTeam(matchEntity.getFirstTeamID());
        TeamEntity secondTeam = this.teamService.getTeam(matchEntity.getSecondTeamID());
        currentMatch.setFirstTeam(firstTeam);
        currentMatch.setSecondTeam(secondTeam);
        currentMatch.getFirstTeam().setPlayers(new ArrayList<>());
        currentMatch.getSecondTeam().setPlayers(new ArrayList<>());
        int tossResult = toss(); // Running toss method
        teamService.resetTeam(currentMatch.getFirstTeam(), currentMatch.getSecondTeam());
        playerService.setPlayers(currentMatch.getFirstTeam(), currentMatch.getSecondTeam());
        currentMatch.setTotalOvers(matchEntity.getNumberOfOvers());
        if (tossResult == 0) { // Playing match according to the output of toss
            inningService.play(currentMatch.getFirstTeam(), true, currentMatch.getSecondTeam());
            inningService.play(currentMatch.getSecondTeam(), false, currentMatch.getFirstTeam());
        } else {
            inningService.play(currentMatch.getSecondTeam(), true, currentMatch.getFirstTeam());
            inningService.play(currentMatch.getFirstTeam(), false, currentMatch.getSecondTeam());
        }
        endMatch(matchEntity);
        return currentMatch.getWinner();
    }

    private void endMatch(MatchEntity matchEntity) {
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

    // Method for paying innings, taking battingTeam and current innings argument
    // isFirstInnings will be true for first innings and false for second innings
    //This function will take both batting and bowling team.


    @Override
    public int toss() {
        return (int) (Math.random() * 2);
    }

    @Override
    public MatchEntity getDetails(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    @Override
    public void createMatch(MatchEntity match) {
        matchRepository.save(match);
    }
}
