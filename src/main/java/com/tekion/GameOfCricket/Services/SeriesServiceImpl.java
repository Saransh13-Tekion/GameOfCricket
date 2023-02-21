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
    public void startSeries(Long id) {
        SeriesEntity seriesEntity = seriesRepository.findById(id).orElse(null);
        int firstTeamWin = 0,secondTeamWin = 0;
        for(Long matchNumber = 1L;matchNumber<=seriesEntity.getNumberOfMatches();matchNumber++) {
            matchService.createMatch(new MatchEntity(seriesEntity.getFirstTeamID(), seriesEntity.getSecondTeamID(), seriesEntity.getNumberOfOvers()));
            Long winner = matchService.startMatch(matchNumber);
            if(seriesEntity.getFirstTeamID() == winner){
                firstTeamWin++;
            }
            else{
                secondTeamWin++;
            }
        }
        seriesEntity.setWinner(firstTeamWin>secondTeamWin? seriesEntity.getFirstTeamID() : seriesEntity.getSecondTeamID());
        seriesRepository.save(seriesEntity);
    }
}
