package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.DTO.ResponseDTO;
import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /** Add players in database.
     * @param players The players that are to be added in te database
     * @return
     */
    @PostMapping("/")
    public ResponseDTO add(@Valid @RequestBody List<PlayerEntity>players){
        playerService.addPlayer(players);
        return new ResponseDTO(true,"none");
    }

    /** This method gets the required player from the database
     * @param id id of the required player that needs to be fetched from the database
     * @return
     */
    @GetMapping("/{id}")
    public PlayerEntity get(@PathVariable Long id){
        return playerService.getPlayer(id);
    }
}
