/*********************************************************
 * 
 * Class Name: Main.java
 * Clase que inicia el programa creando la listas y llamando
 * a las funciones de otras clases para ejecutar el programa.
 * 
 * @author Alberto V�zquez Mart�nez y �ngel Villafranca Iniesta
 * 
 *********************************************************/

package P4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {

	final static int NUMBERS = 6;
	final static int LOWER_LIMIT_TARGET_NUMBER = 100;
	final static int UPPER_LIMIT_TARGET_NUMBER = 1000;
	final static Scanner KEYBOARD = new Scanner(System.in);
	
	/**
	 * Funci�n main que crea las listas de n�meros y llama al resto de funciones del programa
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> listBigNnumbers = Arrays.asList(25,50,75,100);
		List<Integer> listSmallNumbers = Arrays.asList(1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10);
		
		ArrayList<Integer> bigNumbers = new ArrayList<Integer>();
		ArrayList<Integer> smallNumbers = new ArrayList<Integer>();
		
		bigNumbers.addAll(listBigNnumbers);
		smallNumbers.addAll(listSmallNumbers);
		
		int numBigNumbers = requestBigNumbers();
		int numSmallNumbers = selectSmallNumbers(numBigNumbers);

		int[] list= Generator.generateNumbersList(numBigNumbers, numSmallNumbers, bigNumbers, smallNumbers);
		
		Solver solver = new Solver(list, Generator.generateRandomNumber(LOWER_LIMIT_TARGET_NUMBER, UPPER_LIMIT_TARGET_NUMBER));
		solver.solve();
		
		if (solver.getSolutionCount() > 0) {
			System.out.println("\nSe han encontrado " + solver.getSolutionCount() + " soluciones posibles");
			System.out.println("\nLas mejores soluciones son:");
			for (int i = 0; i < solver.getBestSolutionCount(); i++) {
				System.out.println("Soluci�n " + (i+1) + ": " + solver.getSolutions().get(i));
			}
        }
        else {
            System.out.println("No se ha encontrado ninguna soluci�n posible.");
        }
	}
	
	/**
	 * Solicita al usuario el n�mero de n�meros grandes
	 * @return
	 */
	public static int requestBigNumbers() {
		int value = -1;
		
		do {
			try {
				System.out.println("Introduzca el n�mero de numeros grandes a seleccionar (0-4): ");
				value = KEYBOARD.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Error leyendo entero.");
				KEYBOARD.next();
			}
			
		} while (value < 0 || value > 4);
		
		return value;
	}
	
	/**
	 * Establece el n�mero de n�meros peque�os
	 * necesarios para completar los 6 n�meros totales
	 * @param numBigNumbers
	 * @return
	 */
	public static int selectSmallNumbers(int numBigNumbers) {
		return NUMBERS - numBigNumbers; 
	}
}
