package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;

public interface MatchService {
    Long startMatch(Long matchId);
    int toss();
    MatchEntity getDetails(Long id);
    Long createMatch(MatchEntity match);
}
