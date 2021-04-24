/*********************************************************
 * 
 * Class Name: Generator.java
 * Clase que se encarga de generar la lista de números
 * semi-aleatorios que será utilizada para resolver el problema.
 * 
 * @author Alberto Vázquez Martínez y Ángel Villafranca Iniesta
 * 
 *********************************************************/

package P4;

import java.util.ArrayList;

public class Generator {

	/**
	 * Genera la lista con los números a utilizar y el número objetivo a calcular.
	 * @param numBigNumbers
	 * @param numSmallNumbers
	 * @param bigNumbers
	 * @param smallNumbers
	 * @return
	 */
	public static ArrayList<Integer> generateNumbersList(int numBigNumbers, int numSmallNumbers, ArrayList<Integer> bigNumbers, ArrayList<Integer> smallNumbers) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < numBigNumbers; i++) {
			int numberPos = generateRandomNumber(bigNumbers.size()-1, 0);
			list.add(bigNumbers.remove(numberPos));
		}
		
		for (int i = 0; i < numSmallNumbers; i++) {
			int numberPos = generateRandomNumber(smallNumbers.size()-1, 0);
			list.add(smallNumbers.remove(numberPos));
		}
		
		list.add(generateRandomNumber(999, 101)); //El último elemento de la lista es el número objetivo
		
		return list;
	}
	
	/**
	 * Genera un número random dado un mínimo y un máximo
	 * @param max
	 * @param min
	 * @return número random
	 */
	public static int generateRandomNumber(int max, int min) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
}
