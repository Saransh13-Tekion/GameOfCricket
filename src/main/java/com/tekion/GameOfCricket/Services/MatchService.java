package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;

public interface MatchService {
    /** This method start the match
     * @param matchId takes id of the match the needs to be started
     * @return Return match id
     * @throws MissingDataException when the match of given id is not present in database
     * @throws ValidationException some wrong input
     */
    Long startMatch(Long matchId) throws MissingDataException, ValidationException;

    /** This method finds the required match from the database through its id
     * @param id the id of the match that needs to be found
     * @return returns the required match
     */
    MatchEntity getDetails(Long id);

    /** This method will take a matchEntity and save it in the database
     * @param match The match that needs to be saved
     * @return returns match id in the database.
     */
    Long createMatch(MatchEntity match);
}
