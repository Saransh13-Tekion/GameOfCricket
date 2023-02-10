package com.tekion.GameOfCricket;

import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Services.MatchServiceImpl;
import com.tekion.GameOfCricket.Services.PitchServiceImpl;
import com.tekion.GameOfCricket.Services.PlayerServiceImpl;
import com.tekion.GameOfCricket.Services.ScoreBoardServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GameOfCricketApplication {

	public static void main(String[] args) {

		//SpringApplication.run(GameOfCricketApplication.class, args);

		System.out.print("Enter Number of Overs: ");
		Scanner sc = new Scanner(System.in);
		MatchServiceImpl match = new MatchServiceImpl(sc.nextInt(),new PlayerServiceImpl(),new Match(),new ScoreBoardServiceImpl(),new PitchServiceImpl()); // Asking User for Number of Overs.
		match.startMatch();
	}
}
