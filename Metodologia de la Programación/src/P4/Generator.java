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
	public static int[] generateNumbersList(int numBigNumbers, int numSmallNumbers, ArrayList<Integer> bigNumbers, ArrayList<Integer> smallNumbers) {
		int[] list = new int[numBigNumbers+numSmallNumbers];
		int i = 0;
		
		for (i = 0; i < numBigNumbers; i++) {
			int numberPos = generateRandomNumber(0, bigNumbers.size());
			list[i] = bigNumbers.remove(numberPos);
		}
		
		for (int j = i; j < numBigNumbers+numSmallNumbers; j++) {
			int numberPos = generateRandomNumber(0, smallNumbers.size());
			list[j] = smallNumbers.remove(numberPos);
		}

		return list;
	}
	
	/**
	 * Genera un número random dado un mínimo y un máximo
	 * @param max
	 * @param min
	 * @return número random
	 */
	public static int generateRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
