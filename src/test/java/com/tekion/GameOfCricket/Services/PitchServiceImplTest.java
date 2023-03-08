package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.PlayerDTO;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PitchServiceImplTest {
    @InjectMocks
    PitchServiceImpl pitchService;

    private PlayerDTO player1, player2;

    @BeforeEach
    void beforeMethod(){
        player1 = new PlayerDTO();
        player2 = new PlayerDTO();
    }

    @Test
    void getStrikerTest() {
        pitchService.setStriker(player1);
        Assert.assertEquals(player1,pitchService.getStriker());
    }

    @Test
    void getNonStrikerTest() {
        pitchService.setOpeners(player1,player2);
        Assert.assertEquals(player2,pitchService.getNonStriker());
    }

    @Test
    void setOpenersTest() {
        pitchService.setOpeners(player1,player2);
        Assert.assertEquals(player1,pitchService.getStriker());
        Assert.assertEquals(player2,pitchService.getNonStriker());
    }

    @Test
    void swapTest() {
        pitchService.setOpeners(player1,player2);
        pitchService.swap();
        Assert.assertEquals(player2,pitchService.getStriker());
        Assert.assertEquals(player1,pitchService.getNonStriker());
    }

    @Test
    void setStriker() {
        pitchService.setStriker(player1);
        Assert.assertEquals(player1,pitchService.getStriker());
    }

    @Test
    void getCurrentBowler() {
        pitchService.setCurrentBowler(player1);
        Assert.assertEquals(player1,pitchService.getCurrentBowler());
    }

    @Test
    void setCurrentBowler() {
        pitchService.setCurrentBowler(player1);
        Assert.assertEquals(player1,pitchService.getCurrentBowler());
    }
}