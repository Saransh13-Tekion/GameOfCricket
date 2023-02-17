package com.tekion.GameOfCricket.Controllers;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Services.PlayerService;
import com.tekion.GameOfCricket.Services.PlayerServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @PostMapping("/addPlayer")
    public String addPlayer(@RequestBody PlayerEntity player){
        PlayerServiceImpl.addInPlayerTable(player);
        return "Player Added.";
    }

    @PostMapping("/getPlayer")
    public PlayerEntity getPlayer(@RequestBody PlayerEntity player){
        System.out.println(PlayerServiceImpl.getFromPlayerTable(player.getId()));
        return PlayerServiceImpl.getFromPlayerTable(player.getId());
    }
}
