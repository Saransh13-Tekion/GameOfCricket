package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.SeriesEntity;

public interface SeriesService {
    void createSeries(SeriesEntity seriesEntity);
    void startSeries(Long id);
    SeriesEntity getSeries(Long id);
}
