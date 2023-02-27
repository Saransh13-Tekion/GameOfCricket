package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesServiceImpl implements SeriesService{

    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private MatchService matchService;

    @Override
    public void createSeries(SeriesEntity seriesEntity) {
        seriesRepository.save(seriesEntity);
    }

    @Override
    public SeriesEntity getSeries(Long id){
        return seriesRepository.findById(id).orElse(null);
    }

    @Override
    public void startSeries(Long id) {
        SeriesEntity seriesEntity = seriesRepository.findById(id).orElse(null);
        int firstTeamWin = 0,secondTeamWin = 0;
        for(Long matchNumber = 1L;matchNumber<=seriesEntity.getNumberOfMatches();matchNumber++) {
            MatchEntity match = new MatchEntity(seriesEntity.getFirstTeamID(), seriesEntity.getSecondTeamID(), seriesEntity.getNumberOfOvers(),seriesEntity.getId());
            Long matchId = matchService.createMatch(match);
            Long winner = matchService.matchPreparation(matchId);
            if(seriesEntity.getFirstTeamID() == winner){
                firstTeamWin++;
            }
            else{
                secondTeamWin++;
            }
        }
        seriesEntity.setWinner(firstTeamWin>secondTeamWin? seriesEntity.getFirstTeamID() : seriesEntity.getSecondTeamID());
        seriesEntity.setMatchesFirstTeamWon(firstTeamWin);
        seriesEntity.setMatchesSecondTeamWon(secondTeamWin);
        seriesRepository.save(seriesEntity);
    }
}
