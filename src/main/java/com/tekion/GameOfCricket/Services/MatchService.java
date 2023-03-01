package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.Models.Match;

public interface MatchService {
    Long startMatch(Long matchId) throws MissingDataException, ValidationException;
    int toss();
    MatchEntity getDetails(Long id);
    Long createMatch(MatchEntity match);
}
