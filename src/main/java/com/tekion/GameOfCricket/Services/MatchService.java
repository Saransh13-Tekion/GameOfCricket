package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.MatchEntity;

public interface MatchService {
    Long startMatch(MatchEntity matchEntity);
    int toss();
    MatchEntity getDetails(Long id);
    Long createMatch(MatchEntity match);
    Long matchPreparation(Long matchId);
}
