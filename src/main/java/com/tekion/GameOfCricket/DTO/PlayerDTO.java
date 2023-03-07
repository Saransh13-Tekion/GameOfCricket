package com.tekion.GameOfCricket.DTO;

import com.tekion.GameOfCricket.Enums.PlayerRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private int wicketsTaken;
    private boolean gotOut;
    @Builder.Default
    private int runs = 0;
    @Builder.Default
    private int ballsPlayed = 0;
    private Long teamID;
    private PlayerRole role;

}
