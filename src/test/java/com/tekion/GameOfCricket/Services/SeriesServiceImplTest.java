package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.SQLRepository.SeriesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
    void testStartSeries() throws ValidationException, MissingDataException {
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
    void startSeriesValidationFailureTest(){
        try{
            seriesService.startSeries(1L);
        }
        catch (Exception e){
            Assert.assertEquals("Cannot find given series entity",e.getMessage());
        }
    }

    @Test
    void getSeriesTest() throws MissingDataException {
        SeriesEntity series = new SeriesEntity();
        when(seriesRepository.findById(1L)).thenReturn(Optional.of(series));
        Assert.assertEquals(series,seriesService.getSeries(1L));
    }

    @Test
    void createSeriesTest(){
        SeriesEntity series = new SeriesEntity();
        when(seriesRepository.save(series)).thenReturn(series);
        seriesService.createSeries(series);
        Assert.assertTrue(!series.getCreatedAt().equals(null));
    }
}