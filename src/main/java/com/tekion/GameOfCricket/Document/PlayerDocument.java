package com.tekion.GameOfCricket.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
public class PlayerDocument {
    @Id
    private Long id;
    private String name;
    private int wicketsTaken;
    private int runs = 0;
    private int ballsPlayed = 0;
    private Long teamID;
    private String role;

    public PlayerDocument(Long id){
        this.id = id;
    }
}
