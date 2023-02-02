package com.tekion.GameOfCricket;

import com.tekion.GameOfCricket.Models.Match;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

//Basic Changes to be done - Add more functionality to toss function.
//Made instance variables private
//Thinking of adding more functionalities overall.
//How to decide number of batsman

@SpringBootApplication
public class GameOfCricketApplication {

	public static void main(String[] args) {

		SpringApplication.run(GameOfCricketApplication.class, args);

		System.out.print("Enter Number of Overs: ");
		Scanner sc = new Scanner(System.in);
		Match m = new Match(sc.nextInt()); // Asking User for Number of Overs.
	}
}
