package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.SQLRepository.SeriesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SeriesServiceImpl implements SeriesService{

    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private MatchService matchService;
    static Logger log = LogManager.getLogger(SeriesServiceImpl.class);

    @Override
    public void createSeries(SeriesEntity seriesEntity) {
        seriesEntity.setCreatedAt(LocalDateTime.now());
        seriesRepository.save(seriesEntity);
    }

    @Override
    public SeriesEntity getSeries(Long id) throws MissingDataException {
        return seriesRepository.findById(id).orElseThrow(() -> new MissingDataException("Cannot find given series entity"));
    }

    @Override
    public Long startSeries(Long id) throws MissingDataException, ValidationException {
        log.info("Starting Series of id " + id);
        SeriesEntity seriesEntity = seriesRepository.findById(id).orElseThrow(() -> new MissingDataException("Cannot find given series entity"));
        int firstTeamWin = 0,secondTeamWin = 0;
        for(Long matchNumber = 1L;matchNumber<=seriesEntity.getNumberOfMatches();matchNumber++) {
            MatchEntity match = MatchEntity.builder()
                    .firstTeamID(seriesEntity.getFirstTeamID())
                    .secondTeamID(seriesEntity.getSecondTeamID())
                    .numberOfOvers(seriesEntity.getNumberOfOvers())
                    .seriesID(seriesEntity.getId())
                    .build();
            Long matchId = matchService.createMatch(match);
            Long winner = matchService.startMatch(matchId);
            if(seriesEntity.getFirstTeamID().equals(winner)){
                firstTeamWin++;
            }
            else{
                secondTeamWin++;
            }
        }
        seriesEntity.setWinner(firstTeamWin>secondTeamWin? seriesEntity.getFirstTeamID() : seriesEntity.getSecondTeamID());
        seriesEntity.setMatchesFirstTeamWon(firstTeamWin);
        seriesEntity.setMatchesSecondTeamWon(secondTeamWin);
        seriesEntity.setUpdatedAt(LocalDateTime.now());
        seriesRepository.save(seriesEntity);
        log.info("Exiting the Start Series method of id " + id);
        return seriesEntity.getWinner();
    }
}
