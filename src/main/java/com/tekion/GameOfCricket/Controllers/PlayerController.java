package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/addPlayer")
    public String addPlayer(@RequestBody PlayerEntity player){
        playerService.addPlayer(player);
        return "Player Added.";
    }

    @PostMapping("/getPlayer")
    public PlayerEntity getPlayer(@RequestBody PlayerEntity player){
        return playerService.getPlayer(player.getId());
    }
}
