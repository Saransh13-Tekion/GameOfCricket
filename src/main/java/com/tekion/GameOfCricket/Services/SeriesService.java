package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;

public interface SeriesService {
    /** create the scoreboard record in the table
     * @param seriesEntity the entity that needs to be saved.
     */
    void createSeries(SeriesEntity seriesEntity);

    /** starts the series
     * @param id takes the id of the series
     * @throws MissingDataException if the given id is missing from the databse.
     * @throws ValidationException invalid data.
     */
    void startSeries(Long id) throws MissingDataException, ValidationException;

    /** gets the record of the series from the database.
     * @param id id the required series.
     * @return returns the scoreboard record
     */
    SeriesEntity getSeries(Long id) throws MissingDataException;
}
