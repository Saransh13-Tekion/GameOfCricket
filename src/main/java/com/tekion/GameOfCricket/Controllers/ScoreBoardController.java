package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import com.tekion.GameOfCricket.Services.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scoreCard")
public class ScoreBoardController {

    @Autowired
    private ScoreBoardService scoreBoardService;

    @GetMapping("/getRecord")
    public ScoreBoardEntity getRecord(@RequestBody ScoreBoardEntity scoreBoardEntity){
        return scoreBoardService.getRecord(scoreBoardEntity);
    }
}
