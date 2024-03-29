/*********************************************************
 * 
 * Class Name: Main.java
 * Clase que genera una serie de objetos Player con atributos
 * aleatorios, encuentra al jugador impostor y comprueba
 * quien gana la partida.
 * 
 * @author Alberto V�zquez Mart�nez y �ngel Villafranca Iniesta
 * 
 *********************************************************/

package P2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {

	final static Scanner KEYBOARD = new Scanner(System.in);
	
	/**
	 * M�todo principal que llama a los dem�s m�todos
	 * @param args
	 */
	public static void main(String[] args) {
		int impostorPosition = 0;
		int playersNumber = 0;
		long t1, t0;
		double t = 0;
		ArrayList<Player> players = new ArrayList<Player>();
		
		playersNumber = requestNumbersPlayers();
		createPlayers(playersNumber, players);
		t0 = System.nanoTime();
		impostorPosition = findImpostor(players, 0, players.size()-1);
		t1 = System.nanoTime();
		t = (t1 - t0)/1e9;
		
		System.out.println("\nImpostor position: " + impostorPosition);
		System.out.println("The " + checkWinner(players, impostorPosition) + " wins the game"); 
		System.out.println("Time for " + playersNumber + " players: " + t + " (s)");
	}
	
	public static int iterativeMethod(ArrayList<Player> players) {
		int posicion = -1;
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getRage() == 2) {
				posicion = i;
				break;
			}
		}
		return posicion;
	}
	
	/**
	 * Comprueba quien gana la partida respecto
	 * a las tareas y experiencia del Impostor y
	 * el jugador a su derecha.
	 * @param players Lista de jugadores generados aleatoriamente
	 * @param impostorPosition Posicion de la lista del jugador impostor
	 * @return Devuelve el nombre del jugador/es ganador/es
	 */
	public static String checkWinner(ArrayList<Player> players, int impostorPosition) {
		
		int rPlayerPosition = 0;
		String winner = "";
		
		rPlayerPosition = (impostorPosition+1)%players.size();
		
		int impostorTasks = players.get(impostorPosition).getTasks();
		int rPlayerTasks = players.get(rPlayerPosition).getTasks();
		
		System.out.println("Impostor tasks= " + impostorTasks);
		System.out.println("Player tasks= " + rPlayerTasks);
		
		if (impostorTasks > rPlayerTasks) {
			winner = "impostor";
		} else if (rPlayerTasks > impostorTasks) {
			winner = "crew";
		} else {
			int impostorExperience = players.get(impostorPosition).getExperience();
			int rPlayerExperience = players.get(rPlayerPosition).getExperience();

			if (impostorExperience > rPlayerExperience) {
				winner = "crew";
			} else if (rPlayerExperience > impostorExperience) {
				winner = "crew";
			} else {
				winner = "crew";
			}
		}
		
		return winner;
	}
	
	/**
	 * Solicita el numero de jugadores a generar
	 * y devuelve el valor.
	 * @return Devuelve el numero de jugadores a generar
	 */
	public static int requestNumbersPlayers() {
		
		int playersNumber = 0;
		
		do {
			try {
				System.out.println("Introduzca el n�mero de jugadores: ");
				playersNumber = KEYBOARD.nextInt();
				
			} catch (InputMismatchException e) {
				System.out.println("Error leyendo entero.");
				KEYBOARD.next();
			}

		} while (playersNumber <= 2);
		
		return playersNumber;
	}
	
	/**
	 * Crea los jugadores y genera aleatoriamente 
	 * los atributos de cada uno de los jugadores.
	 * @param playersNumber Numero de jugadores de la partida
	 * @param players Lista de jugadores aleatorios
	 */
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
	
	/**
	 * Genera un numero entero aleatorio entre un rango especificado
	 * @param min Valor rango minimo
	 * @param max Valor rango maximo
	 * @return Valor entero generado
	 */
	public static int generateRandomNumber(int min, int max) {
		int number = 0;
		Random rn = new Random();	
		number = rn.nextInt(max - min + 1) + min;
		return number;
	}

	/**
	 * Encuentra el jugador impostor de una lista 
	 * de jugadores de manera recursiva. 
	 * Compara la suma de las iras de dos "sublistas".
	 * @param players Lista de jugadores aleatorios
	 * @param lowerLimit Limite inferior de la lista
	 * @param upperLimit Limite superior de la lista
	 * @return Ira del jugador
	 */
	public static int findImpostor(ArrayList<Player> players, int lowerLimit, int upperLimit) {
		int pos =0;

		if (lowerLimit == upperLimit) {
			pos = lowerLimit;
			
		} else {
			
			int middle = (lowerLimit+upperLimit)/2;
			if ((upperLimit-lowerLimit) % 2 == 0) { //Numero de jugadores impares
				
				int rageLeft = sumRage(players, lowerLimit, middle-1);
				int rageRight = sumRage(players, middle+1, upperLimit);

				if (rageLeft > rageRight) {
						pos = findImpostor(players, lowerLimit, middle-1);
				} else if (rageLeft < rageRight) {
					pos = findImpostor(players, middle+1, upperLimit);
				}
				else {
					pos = middle;
				}
			} else { //Numero de jugadores pares

				int rageLeft = sumRage(players, lowerLimit, middle);
				int rageRight = sumRage(players, middle+1, upperLimit);
				
				if (rageLeft > rageRight) {
					pos=findImpostor(players, lowerLimit, middle);
				} else if (rageLeft < rageRight) {
					pos=findImpostor(players, middle+1, upperLimit);
				}
			}
		}
		return pos;
	}
	
	/**
	 * Realiza la suma de las iras y devuelve la suma total desde
	 * el limite inferior y el limite superior
	 * @param players
	 * @param lowerLimit
	 * @param upperLimit
	 * @return Suma de los dos lados
	 */
	public static int sumRage(ArrayList<Player> players, int lowerLimit, int upperLimit) {
		int rage = 0;
		
		for (int i = lowerLimit; i <= upperLimit; i++) {
			rage = rage + players.get(i).getRage();
		}
		
		return rage;
	}
}
