package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.DTO.MatchDTO;

public interface InningService {
    /** Through this method, an entire inning wll be played out
     * @param currentMatch Contains all details of the current MatchDTO
     * @param battingTeam Contains all details of the batting team
     * @param isFirstInnings It tells whether it is first innings.
     * @param bowlingTeam Contains all details of the bowling team
     */
    void play(MatchDTO currentMatch, TeamDTO battingTeam, boolean isFirstInnings, TeamDTO bowlingTeam) throws ValidationException;
}
