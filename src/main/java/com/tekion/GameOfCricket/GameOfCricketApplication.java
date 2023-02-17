package com.tekion.GameOfCricket;

import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.Scanner;

@SpringBootApplication
public class GameOfCricketApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		SpringApplication.run(GameOfCricketApplication.class, args);

		//MatchServiceImpl match = new MatchServiceImpl(20,new PlayerServiceImpl(),new Match(),new PitchServiceImpl(),new ScoreBoardServiceImpl()); // Asking User for Number of Overs.
		//for(int i = 0;i<10;i++)
		//	match.startMatch();
	}
}
