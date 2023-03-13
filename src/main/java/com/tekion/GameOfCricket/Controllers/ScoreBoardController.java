package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.ElasticSearchDocuments.ScoreBoardES;
import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import com.tekion.GameOfCricket.Services.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/scoreCard")
public class ScoreBoardController {

    @Autowired
    private ScoreBoardService scoreBoardService;

    /**
     * gets the record from scoreboard table
     *
     * @param matchId  match id of the record
     * @param playerId player id of the player
     * @return the record from the scoreboard
     */
    @GetMapping("/{matchId}/{playerId}")
    public ScoreBoardES getRecord(@PathVariable Long matchId, @PathVariable Long playerId){
        return scoreBoardService.getRecord(matchId,playerId);
    }
}
