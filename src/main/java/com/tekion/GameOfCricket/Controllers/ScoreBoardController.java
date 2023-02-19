package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import com.tekion.GameOfCricket.Services.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scoreCard")
public class ScoreBoardController {

    @Autowired
    private ScoreBoardService scoreBoardService;

    @PostMapping("/getRecord")
    public ScoreBoardEntity getRecord(@RequestBody ScoreBoardEntity scoreBoardEntity){
        return scoreBoardService.getRecord(scoreBoardEntity);
    }
}
