package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Exception.ValidationException;
import com.tekion.GameOfCricket.DTO.TeamDTO;

import java.util.List;

public interface PlayerService {

    /** This method add players in the database
     * @param players The players that are to be added in te database
     */
    void addPlayer(List<PlayerEntity> players);

    /** This method gets the required player from the database
     * @param id id of the required player that needs to be fetched from the database
     * @return
     */
    PlayerEntity getPlayer(Long id);

    /** sets the players of their respective teams from the databse.
     * @param firstTeam first team
     * @param secondTeam second team
     * @throws ValidationException
     */
    void setPlayers(TeamDTO firstTeam, TeamDTO secondTeam) throws ValidationException;

    /** It updates the stats of the team after every match.
     * @param team team whose stats needs to be updated
     * @throws MissingDataException if cant find the given team in database.
     */
    void saveStats(TeamDTO team) throws MissingDataException;
}
