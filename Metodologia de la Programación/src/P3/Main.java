package P3;

import java.util.*;

public class Main {

	final static Scanner KEYBOARD = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int numbers[] = new int[2];
		
		requestNumbers(numbers);
		simulateWeek(numbers[0], numbers[1]);
	}

	public static void simulateWeek(int num_eopies, int num_containers) {
		
		for (int i = 0; i < 7 ; i++) {
			ArrayList<Eopie> eopies = new ArrayList<Eopie>();
			ArrayList<Container> containers = new ArrayList<Container>();
			
			generateEopies(eopies, num_eopies);
			generateContainers(containers, num_containers);
			
			distributeContainersToEopies(eopies, containers);
		}
	}
	
	public static void distributeContainersToEopies(ArrayList<Eopie> eopies, ArrayList<Container> containers) {
		
	}
	
	/**
	 * Generate the eopies objects and adding into the list
	 * @param eopies
	 * @param num_eopies
	 */
	public static void generateEopies(ArrayList<Eopie> eopies, int num_eopies) {
		for (int i = 0; i < num_eopies; i++) {
			double maxLiters = generateLiters();
			Eopie eopie = new Eopie(maxLiters);
			eopies.add(eopie);
		}
	}
	
	/**
	 * Generate the containers objects and adding into the list
	 * @param containers
	 * @param num_containers
	 */
	public static void generateContainers(ArrayList<Container> containers, int num_containers) {
		for (int i = 0; i < num_containers; i++) {
			double liters = generateLiters();
			Container container = new Container(liters);
			containers.add(container);
		}
	}
	
	/**
	 * Request the numbers of eopies and containers and store in an array
	 * @param numbers
	 */
	public static void requestNumbers(int numbers[]) {

		boolean repeated = false;
		
		do {
			try {
				if (repeated) 
					System.out.println("\nEl número de eopies debe ser menor que el número de contenedores");
				
				System.out.println("Introduzca el número de Eopies: ");
				numbers[0] = KEYBOARD.nextInt();
				
				System.out.println("Introduzca el número de Contenedores: ");
				numbers[1] = KEYBOARD.nextInt();
				
				repeated = true;

			} catch (InputMismatchException e) {
				System.out.println("Error leyendo entero.");
				KEYBOARD.next();
			}
			
		} while (numbers[1] <= numbers[0]);
	}
	
	/**
	 * Generate random number double numbers 
	 * between 0 and 50 that represents liters.
	 * @return Double generated
	 */
	public static double generateLiters() {
		double liters;
		
		Random rand = new Random();
		int max = 50;
		int min = 0;
		int range = max - min;
		
		double scaled = rand.nextDouble() * range;
		liters = scaled + min;
		
		return liters;
	}
}
