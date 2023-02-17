package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/addPlayer")
    public String add(@RequestBody List<PlayerEntity>players){
        playerService.addPlayer(players);
        return "Players Added.";
    }

    @PostMapping("/getPlayer")
    public PlayerEntity get(@RequestBody PlayerEntity player){
        return playerService.getPlayer(player.getId());
    }
}
