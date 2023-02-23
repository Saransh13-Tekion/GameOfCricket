package com.tekion.GameOfCricket.Controllers;

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
    public String create(@RequestBody SeriesEntity seriesEntity){
        seriesService.createSeries(seriesEntity);
        return "Series Created.";
    }

    @PostMapping("/start/{id}")
    public String start(@PathVariable Long id){
        seriesService.startSeries(id);
        return "Series Started.";
    }

    @GetMapping("/{id}")
    public SeriesEntity get(@PathVariable Long id){
        return seriesService.getSeries(id);
    }

}
