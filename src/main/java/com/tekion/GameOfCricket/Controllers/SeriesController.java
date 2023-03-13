package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.Services.SeriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity create(@Valid @RequestBody SeriesEntity seriesEntity){
        seriesService.createSeries(seriesEntity);
        return ResponseEntity.status(HttpStatus.OK).body(seriesEntity);
    }

    /** starts the series
     * @param id takes the id of the series
     * @throws MissingDataException if the given id is missing from the databse.
     * @throws ValidationException invalid data.
     */
    @PostMapping("/start/{id}")
    public ResponseEntity start(@PathVariable Long id) {
        Long winnerId;
        try {
            winnerId = seriesService.startSeries(id);
        }
        catch (MissingDataException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (ValidationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Id of Winning Team is " + winnerId);
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
