package P2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

	static int impostorPosition = 0;
	final static Scanner KEYBOARD = new Scanner(System.in);
	
	public static void main(String[] args) {
		int playersNumber = 0;

		ArrayList<Player> players = new ArrayList<Player>();
		
		playersNumber = requestNumbersPlayers();
		createPlayers(playersNumber, players);
		findImpostor(players, 0, players.size()-1);

		/*
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).toString());
		}*/
		
		/*System.out.println(impostorPosition);*/
		System.out.println("The " + checkWinner(players, impostorPosition) + " wins the game"); 
	}
	
	public static String checkWinner(ArrayList<Player> players, int impostorPosition) {
		
		int rPlayerPosition = 0;
		String winner = "";
		
		if (impostorPosition == players.size()-1) {
			rPlayerPosition = 0;
		} else {
			rPlayerPosition = impostorPosition+1;
		}
		
		int impostorTasks = players.get(impostorPosition).getTasks();
		int rPlayerTasks = players.get(rPlayerPosition).getTasks();
		
		/*System.out.println("Impostor tasks= " + impostorTasks);
		System.out.println("Player tasks= " + rPlayerTasks);*/
		
		if (impostorTasks > rPlayerTasks) {
			winner = "impostor";
		} else if (rPlayerTasks > impostorTasks) {
			winner = "tripulation";
		} else {
			int impostorExperience = players.get(impostorPosition).getExperience();
			int rPlayerExperience = players.get(rPlayerPosition).getExperience();

			if (impostorExperience > rPlayerExperience) {
				winner = "impostor";
			} else if (rPlayerExperience > impostorExperience) {
				winner = "tripulation";
			} else {
				winner = "tripulation";
			}
		}
		
		return winner;
	}
	
	public static int requestNumbersPlayers() {
		
		int playersNumber = 0;
		
		do {
			System.out.println("Introduzca el número de jugadores: ");
			playersNumber = KEYBOARD.nextInt();
		} while (playersNumber <= 2);
		
		return playersNumber;
	}
	
	public static void createPlayers(int playersNumber, ArrayList<Player> players) {
		
		int experience = 0;
		int tasks = 0;
		int rage = 0;
		int impostorPosition = generateRandomNumber(0,playersNumber-1);
		
		for (int i = 0; i < playersNumber; i++) {
			
			experience = generateRandomNumber(0,5);
			tasks = generateRandomNumber(0,8);
			
			if (i == impostorPosition) {
				rage = 2;
			} else {
				rage = 1;
			}
			
			Player player = new Player(experience, tasks, rage);
			players.add(player);
		}
	}
	
	public static int generateRandomNumber(int min, int max) {
		int number = 0;
		Random rn = new Random();	
		number = rn.nextInt(max - min + 1) + min;
		return number;
	}
	
	public static int findImpostor(ArrayList<Player> players, int lowerLimit, int upperLimit) {
		int rage = -1;
		int rageLeft = 0;
		int rageRight = 0;
		
		if (lowerLimit==upperLimit) {
			rage = players.get(lowerLimit).getRage();
		} else {
			int middle = (lowerLimit + upperLimit)/2;
			rageLeft += findImpostor(players, lowerLimit, middle);
			rageRight += findImpostor(players, middle+1, upperLimit);
			
			if (rageRight != -1 && rageLeft != -1) {
				if (rageLeft == rageRight) {
					rage = rageLeft+rageRight;
				} else if (rageLeft < rageRight) {
					impostorPosition = lowerLimit+1;
				} else if (rageRight < rageLeft) {
					impostorPosition = upperLimit-1;
				}
			}

		}

		return rage;
	}
}
