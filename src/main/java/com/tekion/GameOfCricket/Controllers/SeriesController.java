package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.DTO.ResponseDTO;
import com.tekion.GameOfCricket.Entity.SeriesEntity;
import com.tekion.GameOfCricket.Services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody SeriesEntity seriesEntity){
        seriesService.createSeries(seriesEntity);
        return new ResponseDTO(true,"none");
    }

    @PostMapping("/start/{id}")
    public ResponseDTO start(@PathVariable Long id){
        seriesService.startSeries(id);
        return new ResponseDTO(true,"none");
    }

    @GetMapping("/{id}")
    public SeriesEntity get(@PathVariable Long id){
        return seriesService.getSeries(id);
    }

}
