package com.tekion.GameOfCricket.Controllers;


import com.tekion.GameOfCricket.Entity.TeamEntity;
import com.tekion.GameOfCricket.Exception.MissingDataException;
import com.tekion.GameOfCricket.Services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    /** Add teams in the database
     * @param teams teams that are to be added in the database
     */
    @PostMapping("/")
    public ResponseEntity addTeam(@Valid @RequestBody List<TeamEntity> teams){
        teamService.addTeam(teams);
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    /** gets team record from the database
     * @param id id of the team that need to be retrieved.
     * @return required Team record
     * @throws MissingDataException if the data is missing from the table.
     */
    @GetMapping("/{id}")
    public TeamEntity getTeam(@PathVariable Long id) throws MissingDataException {
        return teamService.getTeam(id);
    }
}
