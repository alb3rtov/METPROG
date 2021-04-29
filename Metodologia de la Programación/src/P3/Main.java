/*********************************************************
 * 
 * Class Name: Main.java
 * Clase que inicia el programa creando la listas y llamando
 * a las funciones de otras clases para ejecutar el programa.
 * 
 * @author Alberto Vázquez Martínez y Ángel Villafranca Iniesta
 * 
 *********************************************************/

package P3;

import java.util.*;

public class Main {

	final static Scanner KEYBOARD = new Scanner(System.in);
	static int numEopiesEmpty = 0; /* Número de eopies que no transportan ningún contenedor */

	/**
	 * Función principal que solicita datos y genera eopies y containers para los dos primeros algoritmos.
	 * @param args
	 */
	public static void main(String[] args) {
		
		int numbers[] = new int[2];
		double totalLiters;
		
		requestNumbers(numbers);
		
		ArrayList<Eopie> eopies = new ArrayList<Eopie>();
		ArrayList<Container> containers = new ArrayList<Container>();
		
		generateEopies(eopies, numbers[0]);
		generateContainers(containers, numbers[1]);
		
		ArrayList<Eopie> eopiesCopy = (ArrayList<Eopie>) eopies.clone();
		ArrayList<Container> containersCopy = (ArrayList<Container>) containers.clone();
		
		System.out.println("\nNumero de eopies: " + numbers[0]);
		System.out.print("Numero de contenedores: " + numbers[1]);
		
		System.out.println("\n\n***************** Algoritmo más eficiente *****************");
		totalLiters = calculateLiters(eopies, containers, numbers[0], numbers[1], true);
		System.out.printf("\nEl número de litros totales recogidos son de: %.2f litros\n", totalLiters);
		System.out.println("Número de eopies que no han podido transportar ningún contenedor: " + numEopiesEmpty);
		numEopiesEmpty = 0;
		
		System.out.println("\n\n***************** Otro algoritmo menos eficiente (sin ordenación) *****************");
		totalLiters = calculateLiters(eopiesCopy, containersCopy, numbers[0], numbers[1], false);
		System.out.printf("\nEl número de litros totales recogidos son de: %.2f litros\n", totalLiters);
		System.out.println("Número de eopies que no han podido transportar ningún contenedor: " + numEopiesEmpty);
		
		System.out.print("\n\n************************* Simulación 7 días *************************\n");
		totalLiters = simulateWeek(numbers[0], numbers[1]);
		System.out.printf("\n\nNúmero total de litros regidos en una semana: %.2f litros", totalLiters);
		System.out.println("\nNúmero de eopies que no han podido transportar ningún contenedor: " + numEopiesEmpty);
	}

	/**
	 * Simula una semana recogiendo litros de agua
	 * generando cada dias nuevos contenedores y
	 * nuevos litros soportados por cada eopie.
	 * @param num_eopies
	 * @param num_containers
	 * @return
	 */
	public static double simulateWeek(int num_eopies, int num_containers) {
		double totalLiters = 0;
		numEopiesEmpty = 0;
		
		for (int i = 0; i < 7; i++) {
			ArrayList<Eopie> eopies = new ArrayList<Eopie>();
			ArrayList<Container> containers = new ArrayList<Container>();
			
			generateEopies(eopies, num_eopies);
			generateContainers(containers, num_containers);
			
			totalLiters += calculateLiters(eopies, containers, num_eopies, num_containers, true);
		}
		return totalLiters;
	}
	
	/**
	 * Crea y ordena las listas y devuelve el total de litros recogidos
	 * @param num_eopies
	 * @param num_containers
	 * @return
	 */
	public static double calculateLiters(ArrayList<Eopie> eopies, ArrayList<Container> containers, int num_eopies, int num_containers, boolean best) {
		double totalLiters = 0;

		if (best == true) {
			/* Reordernar los eopies de menor a mayor */
			Collections.sort(eopies, Comparator.comparing(Eopie::getMaxLiters));
			
			/* Reordernar los contenedores de mayor a menor */
			Collections.sort(containers, Comparator.comparing(Container::getLiters));
			Collections.reverse(containers);
		}
		
		printInfo(eopies, containers);
		totalLiters = distributeContainersToEopies(eopies, containers);	

		return totalLiters;
	}
	
	/**
	 * Imprime información sobre los eopies y contenedores
	 * @param eopies
	 * @param containers
	 */
	public static void printInfo(ArrayList<Eopie> eopies, ArrayList<Container> containers) {
		System.out.println("\nInformación de los eopies: ");
		for (int i = 0; i < eopies.size(); i++) {
			System.out.printf("%.2f litros, ",eopies.get(i).getMaxLiters());
		}
		
		System.out.println("\nInformación de los contenedores: ");
		for (int i = 0; i < containers.size(); i++) {
			System.out.printf("%.2f litros, ",containers.get(i).getLiters());
		}
	}

	/**
	 * Comprueba si existe algún contenedor que el eopie pueda llevar
	 * @param containers
	 * @param eopie
	 * @return
	 */
	public static boolean isFeasibleVolume(ArrayList<Container> containers, Eopie eopie) {
		boolean feasible = false;
		
		for (int i = 0; i < containers.size(); i++) {
			if (eopie.getMaxLiters() >= containers.get(i).getLiters()) {
				feasible = true;
				eopie.setLitersCarrying(containers.get(i).getLiters());
				containers.remove(i);
				break;
			}
		}
		
		return feasible;
	}
	
	/**
	 * Método voraz que selecciona un eopie,
	 * y comprueba se existe algún contenedor 
	 * que pueda transportar, si no, 
	 * no transportará ningún contenedor
	 * @param eopies
	 * @param containers
	 * @return
	 */
	public static double distributeContainersToEopies(ArrayList<Eopie> eopies, ArrayList<Container> containers) {
		double totalLiters = 0;
		
		while (!eopies.isEmpty()) {
			Eopie e = eopies.get(0);
			eopies.remove(0);
			
			if (isFeasibleVolume(containers, e)) {
				totalLiters += e.getLitersCarrying();
			} else {
				numEopiesEmpty++;
			}
		}
		
		return totalLiters;
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
