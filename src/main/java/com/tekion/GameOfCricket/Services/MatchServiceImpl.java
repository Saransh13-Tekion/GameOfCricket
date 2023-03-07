package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.MatchDTO;
import com.tekion.GameOfCricket.Entity.*;
import com.tekion.GameOfCricket.Enums.RunGenerationStrategy;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.Repository.MatchRepository;
import com.tekion.GameOfCricket.Services.runGenerator.RunGeneratorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    static Logger log = LogManager.getLogger(MatchServiceImpl.class);

    @Override
    public Long startMatch(Long matchId) throws MissingDataException, ValidationException {
        log.info("In the start match function of match " + matchId);
        MatchEntity matchEntity = matchRepository.findById(matchId).orElseThrow(() -> new MissingDataException("Required team not Found in Database"));
        MatchDTO currentMatchDTO = new MatchDTO();
        if(matchEntity.getFirstTeamID().equals(null) || matchEntity.getSecondTeamID().equals(null)){
            log.error("Any of the 2 Teams is missing.");
            throw new MissingDataException("Incorrect Input of any of the 2 teams.");
        }
        currentMatchDTO.setFirstTeam(teamService.getTeam(matchEntity.getFirstTeamID()));
        currentMatchDTO.setSecondTeam(teamService.getTeam(matchEntity.getSecondTeamID()));
        setStrategy(matchEntity);
        int tossResult = toss(); // Running toss method
        playerService.setPlayers(currentMatchDTO.getFirstTeam(), currentMatchDTO.getSecondTeam());
        currentMatchDTO.setTotalOvers(matchEntity.getNumberOfOvers());
        if (tossResult == 0) { // Playing match according to the output of toss
            inningService.play(currentMatchDTO, currentMatchDTO.getFirstTeam(), true, currentMatchDTO.getSecondTeam());
            inningService.play(currentMatchDTO, currentMatchDTO.getSecondTeam(), false, currentMatchDTO.getFirstTeam());
        } else {
            inningService.play(currentMatchDTO, currentMatchDTO.getSecondTeam(), true, currentMatchDTO.getFirstTeam());
            inningService.play(currentMatchDTO, currentMatchDTO.getFirstTeam(), false, currentMatchDTO.getSecondTeam());
        }
        endMatch(matchEntity, currentMatchDTO);
        log.error("Exiting the start match method.");
        return currentMatchDTO.getWinner();
    }


    private void setStrategy(MatchEntity matchEntity) throws ValidationException {
        if(RunGenerationStrategy.EQUAL.equals(matchEntity.getRunStrategy())) {
            RunGeneratorFactory.runGenerationStrategy = RunGenerationStrategy.EQUAL;
        }
        else{
            RunGeneratorFactory.runGenerationStrategy = RunGenerationStrategy.WEIGHTED;
        }
    }

    private void endMatch(MatchEntity matchEntity, MatchDTO currentMatchDTO) throws MissingDataException {
        log.info("Entering the end match method of match " + matchEntity.getId());
        matchEntity.setWinner(currentMatchDTO.getWinner());
        matchEntity.setUpdatedAt(LocalDateTime.now());
        matchRepository.save(matchEntity);
        scoreBoardService.saveStats(currentMatchDTO.getFirstTeam(), matchEntity);
        scoreBoardService.saveStats(currentMatchDTO.getSecondTeam(), matchEntity);
        playerService.saveStats(currentMatchDTO.getFirstTeam());
        playerService.saveStats(currentMatchDTO.getSecondTeam());
        teamService.saveStats(matchEntity);
    }

    private int toss() {
        return (int) (Math.random() * 2);
    }

    @Override
    public MatchEntity getDetails(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    @Override
    public Long createMatch(MatchEntity match) {
        match.setCreatedAt(LocalDateTime.now());
        log.info("Creating match of match id " + match.getId());
        return matchRepository.save(match).getId();
    }
}
