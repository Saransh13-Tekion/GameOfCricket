package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.DTO.ResponseDTO;
import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.Services.SeriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    /** create the scoreboard record in the table
     * @param seriesEntity the entity that needs to be saved.
     */
    @PostMapping("/create")
    public ResponseDTO create(@Valid @RequestBody SeriesEntity seriesEntity){
        seriesService.createSeries(seriesEntity);
        return new ResponseDTO(true,"none");
    }

    /** starts the series
     * @param id takes the id of the series
     * @throws MissingDataException if the given id is missing from the databse.
     * @throws ValidationException invalid data.
     */
    @PostMapping("/start/{id}")
    public ResponseDTO start(@PathVariable Long id) throws ValidationException, MissingDataException {
        seriesService.startSeries(id);
        return new ResponseDTO(true,"none");
    }

    /** gets the record of the series from the database.
     * @param id id the required series.
     * @return returns the scoreboard record
     */
    @GetMapping("/{id}")
    public SeriesEntity get(@PathVariable Long id) throws MissingDataException {
        return seriesService.getSeries(id);
    }

}
