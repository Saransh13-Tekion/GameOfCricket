package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Repository.SeriesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SeriesServiceImplTest {

    @Mock
    SeriesRepository seriesRepository;
    @Mock
    MatchService matchService;

    @InjectMocks
    SeriesServiceImpl seriesService;


    @Test
    void startSeriesPositive() {
        SeriesEntity seriesEntity = new SeriesEntity();
        seriesEntity.setNumberOfMatches(1);
        seriesEntity.setFirstTeamID(1L);
        seriesEntity.setSecondTeamID(2L);

        when(seriesRepository.findById(1L)).thenReturn(Optional.of(seriesEntity));
        when(matchService.startMatch(1L)).thenReturn(1L);

        seriesService.startSeries(1L);
        Assert.assertEquals(2L ,(long) seriesEntity.getWinner());
    }

    @Test
    void startSeriesNegative(){
        try{
            seriesService.startSeries(1L);
        }
        catch (Exception e){
            Assert.assertEquals("Cannot find given series entity",e.getMessage());
        }
    }
}