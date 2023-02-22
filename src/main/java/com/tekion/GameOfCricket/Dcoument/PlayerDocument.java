package com.tekion.GameOfCricket.Dcoument;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Players")
@NoArgsConstructor
public class PlayerDocument {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Wickets Taken")
    private int wicketsTaken;
    @Column(name = "Runs Made")
    private int runs = 0;
    @Column(name = "Balls Played")
    private int ballsPlayed = 0;
    @Column(name = "TeamID")
    private Long teamID;

    @Column(name = "Role")
    private String role;

    public PlayerDocument(Long id){
        this.id = id;
    }
}
