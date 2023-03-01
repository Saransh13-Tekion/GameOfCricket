package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;

public interface SeriesService {
    void createSeries(SeriesEntity seriesEntity);
    void startSeries(Long id) throws MissingDataException, ValidationException;
    SeriesEntity getSeries(Long id);
}
