/*********************************************************
 * 
 * Class Name: Main.java
 * Clase que inicia el programa creando la listas y llamando
 * a las funciones de otras clases para ejecutar el programa.
 * 
 * @author Alberto Vázquez Martínez y Ángel Villafranca Iniesta
 * 
 *********************************************************/

package P4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class Main {

	final static int NUMBERS = 6;
	final static Scanner KEYBOARD = new Scanner(System.in);
	
	/**
	 * Función main que crea las listas de números
	 * y llama al resto de funciones del programa
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

		ArrayList<Integer> list = Generator.generateNumbersList(numBigNumbers, numSmallNumbers, bigNumbers, smallNumbers);
		Solver.solve(list);
	}
	
	/**
	 * Solicita al usuario el número de números grandes
	 * @return
	 */
	public static int requestBigNumbers() {
		int value;
		
		do {
			System.out.println("Introduzca el número de numeros grandes a seleccionar (0-4): ");
			value = KEYBOARD.nextInt();
		} while (value < 0 || value > 4);
		
		return value;
	}
	
	/**
	 * Establece el número de números pequeños
	 * necesarios para completar los 6 números totales
	 * @param numBigNumbers
	 * @return
	 */
	public static int selectSmallNumbers(int numBigNumbers) {
		return NUMBERS - numBigNumbers; 
	}
}
