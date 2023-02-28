package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import com.tekion.GameOfCricket.Models.Team;
import com.tekion.GameOfCricket.Repository.PlayerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PlayerServiceImplTest<e> {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerServiceImpl playerService;

    Team firstTeam = new Team(1L);
    Team secondTeam = new Team(2L);

    @Test
    public void setPlayers_Positive() {
        List<PlayerEntity> players1 = new ArrayList<>();
        players1.add(new PlayerEntity(1L));
        players1.add(new PlayerEntity(2L));

        when(playerRepository.findAll()).thenReturn(players1);

        playerService.setPlayers(firstTeam,secondTeam);
        Assert.assertEquals(1,firstTeam.getPlayers().size());
    }

    @Test
    public void setPlayer_Negative(){
        List<PlayerEntity> players1 = new ArrayList<>();
        players1.add(new PlayerEntity(1L));

        when(playerRepository.findAll()).thenReturn(players1);

        try{
            playerService.setPlayers(firstTeam,secondTeam);
        }
        catch(Exception e){
            Assert.assertEquals(e.getMessage(),"Unequal number of Players in both teams");
        }
    }

}