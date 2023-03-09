package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.TeamDTO;
import com.tekion.GameOfCricket.Entity.MatchEntity;
import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;

import java.util.List;

public interface TeamService{
    /** Add teams in the database
     * @param teams teams that are to be added in the database
     */
    void addTeam(List<TeamEntity> teams);

    /** gets team record from the database
     * @param id id of the team that need to be retrieved.
     * @return required Team record
     * @throws MissingDataException if the data is missing from the table.
     */
    TeamEntity getTeam(Long id) throws MissingDataException;

    /** Saving match stats.
     * @param matchEntity stats that need to be saved.
     * @throws MissingDataException
     */
    void saveStats(MatchEntity matchEntity) throws MissingDataException;
}
