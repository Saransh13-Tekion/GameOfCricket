package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.ElasticSearchDocuments.ScoreBoardES;
import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.DTO.TeamDTO;


public interface ScoreBoardService {
    /** Save stats of the match in database.
     * @param team team whose stats saved
     * @param match match whose stats need to be saved.
     */
    void saveStats(TeamDTO team, MatchEntity match);

    /**
     * gets the record from scoreboard table
     *
     * @param matchId  match id of the record
     * @param playerId player id of the player
     * @return the record from the scoreboard
     */
    ScoreBoardES getRecord(Long matchId, Long playerId);
}
